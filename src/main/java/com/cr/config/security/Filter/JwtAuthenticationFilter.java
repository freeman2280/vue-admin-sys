package com.cr.config.security.Filter;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cr.config.security.handler.AnonymousAuthenticationHandler;
import com.cr.pojo.User;
import com.cr.utils.JWTUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Value("${url.white_list}")
    List<String> white_list;
    @Value("${server.servlet.context-path}")
    String context_path;
    @Autowired
    StringRedisTemplate stringRedisTemplate;

@Autowired
AnonymousAuthenticationHandler anonymousAuthenticationHandler;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        HttpServletRequest是Servlet中用于获取HTTP请求信息的接口。request.getRequestURI()和request.getRequestURL()都是用于获取请求的URI（Uniform Resource Identifier）信息，但它们有一些区别：
//
//        request.getRequestURI()：
//
//        返回字符串类型的请求URI，不包括请求参数部分。
//        示例：如果请求的URL是http://example.com/context-path/servlet-path/resource?param=value，那么request.getRequestURI()将返回/context-path/servlet-path/resource。
//        request.getRequestURL()：
//
//        返回一个StringBuffer对象，包含完整的请求URL，包括协议、主机、端口、上下文路径、Servlet路径等信息。
//        示例：如果请求的URL是http://example.com:8080/context-path/servlet-path/resource?param=value，那么request.getRequestURL()将返回http://example.com:8080/context-path/servlet-path/resource。


        //获取当前请求的uri地址
        String uri = request.getRequestURI();
        String  s = uri.substring(context_path.length());
        //如果当前请求不是白名单的请求比如登录请求，则需要进行token验证
        if (!white_list.contains(s)) {
            this.validateToken(request);
        }
        //登录请求不需要验证token
        doFilter(request, response, filterChain);
    }

    private void validateToken(HttpServletRequest request) throws AuthenticationException {
        //从头部获取token信息
        String token = request.getHeader("token");
        if(StringUtils.isBlank(token)) {
            anonymousAuthenticationHandler.setMessage("token为空,认证失败!");return;
        }
        try {//验证token时发生异常，则会被自定义Jwt异常捕获，最终通过认证失败处理器返回给前端
            //验证token的合法性
//            System.out.println(111);
            JWTUtils.verify(token);//验证令牌,这里出问题会抛出异常
            //解析token数据，获取用户id
            DecodedJWT decodedJWT = JWTUtils.getToken(token);
            String userID = decodedJWT.getClaim("id").asString();
            String username = decodedJWT.getClaim("username").asString();
            //验证成功后从redis中获取到认证信息并存入SecurityContextHolder
            String authenticationInfo = stringRedisTemplate.opsForValue().get(userID + "_" + username);
            User userDetails = new ObjectMapper().readValue(authenticationInfo, User.class);
            //创建身份验证对象
            List<String> perms = userDetails.getPerms();
            List<SimpleGrantedAuthority> grantedAuthorities=new LinkedList<>();
            perms.forEach(item->{
                grantedAuthorities.add(new SimpleGrantedAuthority(item));
            });
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, grantedAuthorities);
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            //设置到Spring Security上下文
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        } catch (SignatureVerificationException e) {
            e.printStackTrace();
            anonymousAuthenticationHandler.setMessage("无效签名,认证失败!");
        } catch (TokenExpiredException e) {
            e.printStackTrace();
            anonymousAuthenticationHandler.setMessage("token过期,认证失败!");
        } catch (AlgorithmMismatchException e) {
            e.printStackTrace();
            anonymousAuthenticationHandler.setMessage("token算法不一致,认证失败!");
        } catch (Exception e) {
            e.printStackTrace();
            anonymousAuthenticationHandler.setMessage("认证失败!");
        }
    }
}
