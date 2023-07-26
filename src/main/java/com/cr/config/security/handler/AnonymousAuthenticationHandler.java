package com.cr.config.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 匿名用户访问资源处理器，也就是未登录用户
 */

/**
 * AuthenticationException和AuthenticationEntryPoint是Spring Security框架中用于处理认证和授权的两个不同的概念。
 *
 * AuthenticationException：AuthenticationException是Spring Security中的一个异常类，用于表示认证过程中发生的异常。当用户在认证过程中提供的凭据无效或认证失败时，将会抛出AuthenticationException。例如，当用户提供的用户名或密码不正确时，会引发BadCredentialsException。开发人员可以通过捕获和处理AuthenticationException来处理认证过程中的异常情况。
 *
 * AuthenticationEntryPoint：AuthenticationEntryPoint是Spring Security中的一个接口，用于处理未经身份验证的请求。当未经身份验证的用户尝试访问需要身份验证的资源时，会触发AuthenticationEntryPoint来处理该请求。它的主要作用是在用户尚未登录时引导用户进行身份验证，通常是通过重定向到登录页面或返回错误响应。开发人员可以实现AuthenticationEntryPoint接口，并自定义其行为来满足应用程序的需求。
 *
 * 简而言之，AuthenticationException表示认证过程中的异常，而AuthenticationEntryPoint是用于处理未经身份验证的请求的入口点，用于引导用户进行身份验证。
 */
@Component
@Data
public class AnonymousAuthenticationHandler implements AuthenticationEntryPoint {
    String message="请认证之后再去处理!";
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse
            response, AuthenticationException authException) throws IOException,
            ServletException {


        Map<String, Object> result = new HashMap<String, Object>();
        result.put("message", message);
        result.put("code",HttpStatus.UNAUTHORIZED.value());
        response.setStatus(HttpStatus.UNAUTHORIZED.value());//401 表示没有授权
        response.setContentType("application/json;charset=UTF-8");
        String s = new ObjectMapper().writeValueAsString(result);
        response.getWriter().println(s);
    }
}
