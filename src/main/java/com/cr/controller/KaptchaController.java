package com.cr.controller;

import com.cr.utils.Result;
import com.google.code.kaptcha.Producer;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 生成验证码图片（数字）
 * 生成一个随机数字key对应该验证码
 * 将key 和验证码数字保存在redis并设置1分钟超时时间
 * 把验证码转为base64编码，将编码的字符串和key一同返回给前端
 * 前端拿这key和输入的验证码给后端
 * 后端在拿key去redis中查找，看是否过期或者错误
 */
@RestController
public class KaptchaController {
    @Autowired
    Producer producer;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @RequestMapping("/api/getKaptcha")
    Result getKaptcha() throws IOException {
        try {
            String codeText = producer.createText();
            BufferedImage image = producer.createImage(codeText);
            String key = UUID.randomUUID().toString();
            // 将验证码图片转换成base64编码的文本格式
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", baos);
            /**
             * data:image/jpeg;base64, 是一种数据URL（Data URL）的格式。它用于将数据（如图片）嵌入到文档中，而不需要单独的外部文件。
             *
             * 在这个格式中，data:image/jpeg;base64, 表示后面的数据是经过Base64编码的JPEG格式图片数据。实际的图片数据紧随此前缀，以Base64编码的形式出现。
             * 使用数据URL，你可以直接在HTML、CSS、JavaScript等代码中将图片数据内嵌，而不需要单独加载外部图片文件。这对于小型图像或需要避免额外的网络请求的情况很有用。
             *
             * 以下是一个使用数据URL嵌入Base64编码图片的示例：
             * <img src="data:image/jpeg;base64,/9j/4AAQSkZJRgABAQEAAAAAAAAD...">
             */
            String prefix="data:image/jpg;base64,";
            String base64Image = Base64.getEncoder().encodeToString(baos.toByteArray());
            HashMap<String, String> map = new HashMap<>();
            map.put("verifyCodeKey",key);
            map.put("verifyCode",prefix+base64Image);

            //将验证码和key存入redis
            stringRedisTemplate.opsForValue().set(key,codeText,5, TimeUnit.MINUTES);
            return Result.ok(map).message("验证码创建成功");
        }
        catch (Exception e) {
            return Result.error().message("验证码创建失败");
        }

    }
}
