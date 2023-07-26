package com.cr.config.security.Filter;

import com.cr.Exception.KaptchaException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private String verifycodeParameter="verifyCode";
    private String verifycodeKeyParameter="verifyCodeKey";
    public void setVerifycodeParameter(String verifycodeParameter) {
        this.verifycodeParameter = verifycodeParameter;
    }

    public String getVerifycodeParameter() {
        return verifycodeParameter;
    }

    public String getVerifycodeKeyParameter() {
        return verifycodeKeyParameter;
    }

    public void setVerifycodeKeyParameter(String verifycodeKeyParameter) {
        this.verifycodeKeyParameter = verifycodeKeyParameter;
    }

    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        if(request.getContentType().equalsIgnoreCase(MediaType.APPLICATION_JSON_VALUE)) {
           try {
               Map<String,String> map = new ObjectMapper().readValue(request.getInputStream(), Map.class);
               //先验证验证码
               String verifycode = map.get(getVerifycodeParameter());
               String verifycodeKey=map.get(getVerifycodeKeyParameter());
               if(StringUtils.isBlank(verifycodeKey)) {
                   throw new KaptchaException("验证码错误");
               }
               String code = stringRedisTemplate.opsForValue().get(verifycodeKey);
               if(StringUtils.isBlank(code)) {
                   throw new KaptchaException("验证码已超时");
               }
               if(!code.equalsIgnoreCase(verifycode)) throw new KaptchaException("验证码错误");


               String username = map.get(getUsernameParameter());
               String password = map.get(getPasswordParameter());
               UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
               // Allow subclasses to set the "details" property
               setDetails(request, authRequest);
               return this.getAuthenticationManager().authenticate(authRequest);

           } catch (JsonParseException e) {
               e.printStackTrace();
           } catch (JsonMappingException e) {
               e.printStackTrace();
           } catch (IOException e) {
               e.printStackTrace();
           }
        }
        return super.attemptAuthentication(request, response);
    }
}
