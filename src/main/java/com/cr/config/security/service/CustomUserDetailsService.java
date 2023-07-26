package com.cr.config.security.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cr.pojo.Menu;
import com.cr.pojo.MyGrantedAuthority;
import com.cr.pojo.User;
import com.cr.service.UserService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.runtime.directive.Foreach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username",username);
        User user = userService.getOne(userQueryWrapper);
        if(ObjectUtils.isEmpty(user)) {
            throw  new UsernameNotFoundException("用户名错误");
        }

        List<Menu> menuList = userService.getAllMenusByUserID(user.getId());
        //拿到权限编码
        List<String> stringList = menuList.stream()
                .map(menu -> menu.getPerms())
                .filter(perms -> !StringUtils.isBlank(perms))
                .collect(Collectors.toList());

//        //转换成数组
//        String[] strings = stringList.toArray(new String[stringList.size()]);
//        //得到权限列表并设置
//        List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList(strings);
//
//        user.setAuthorities(authorityList);
      user.setPerms(stringList);

        //设置菜单列表
        user.setMenuList(menuList);
        /**
         * 这里通过用户提交登录请求时填的用户名信息到数据库拿到了准确信息，返回它，它在与用户提交登录请求时填的密码进行对比，
         * 如果一致则登录成功（之后在传统登录认证中是使用session，就把用户信息放入上下文）
         * 失败的话就会抛出认证异常，AuthenticationFailureHandler处理
         */
        return user;
    }

}
