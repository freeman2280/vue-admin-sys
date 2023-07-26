package com.cr.config.security.handler;

import com.cr.Exception.KaptchaException;
import com.cr.utils.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录认证失败处理器
 */
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response, AuthenticationException exception) throws
            IOException, ServletException {
        HashMap<String, String> map = new HashMap<>();
        String message = "登录失败";//提示信息

        //判断异常类型
        if(exception instanceof AccountExpiredException){
            message = "账户过期,登录失败！";
        }
        else if(exception instanceof BadCredentialsException){
            message = "用户名或密码错误,登录失败！";
        }else if(exception instanceof CredentialsExpiredException){
            message = "密码过期,登录失败！";
        }else if(exception instanceof DisabledException){
            message = "账户被禁用,登录失败！";
        }else if(exception instanceof LockedException){
            message = "账户被锁,登录失败！";
        }else if(exception instanceof InternalAuthenticationServiceException){
            message = "账户不存在,登录失败！";
        }
        else if(exception instanceof UsernameNotFoundException){
            message = exception.getMessage();
        }
        else if(exception instanceof KaptchaException){
            message = exception.getMessage();
        }
        else{
            message = "登录失败！";
        }
        response.setContentType("application/json;charset=UTF-8");


        response.getWriter().println(new ObjectMapper().writeValueAsString(Result.error().message(message)));

    }
}
