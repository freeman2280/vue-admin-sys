package com.cr.config.security.handler;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.extension.api.R;
import com.cr.pojo.User;
import com.cr.service.MenuService;
import com.cr.utils.JWTUtils;
import com.cr.utils.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
@Component
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
       //

        response.setContentType("application/json;charset=UTF-8");
        try {
            //从头部获取token信息
            String token = request.getHeader("token");
            DecodedJWT decodedJWT = JWTUtils.getToken(token);
            String userID = decodedJWT.getClaim("id").asString();
            String username = decodedJWT.getClaim("username").asString();
            String key=userID+"_"+username;
            stringRedisTemplate.delete(key);
            response.getWriter().println(new ObjectMapper().writeValueAsString(Result.ok().message("注销成功")));
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println( new ObjectMapper().writeValueAsString(Result.error().message("注销失败")));
        }

    }
}
