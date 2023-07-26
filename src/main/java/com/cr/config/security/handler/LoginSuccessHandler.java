package com.cr.config.security.handler;

import com.cr.pojo.User;

import com.cr.utils.JWTUtils;
import com.cr.utils.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication) throws IOException,
            ServletException {
        User user = (User)authentication.getPrincipal();
        user.setPassword("");

        //生成token
        HashMap<String, String> map = new HashMap<>();
        map.put("id",user.getId()+"");
        map.put("username",user.getUsername());
        String token = JWTUtils.getToken(map);

        //将用户认证信息存入redis(用户id+"_"+用户名作为key)
        stringRedisTemplate.opsForValue().set(user.getId()+"_"+user.getUsername(),new ObjectMapper().writeValueAsString(authentication.getPrincipal()),7, TimeUnit.DAYS);
//        封装返回结果
        response.setContentType("application/json;charset=UTF-8");
        Map<String, Object> res = new HashMap<>();
        res.put("token",token);
        res.put("user", user);

        response.getWriter().write(new ObjectMapper().writeValueAsString(Result.ok(res).message("登录成功")));


    }
}
