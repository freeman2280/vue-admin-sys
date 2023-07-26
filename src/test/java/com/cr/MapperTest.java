package com.cr;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cr.mapper.UserMapper;
import com.cr.pojo.Menu;
import com.cr.pojo.User;
import com.cr.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootTest
public class MapperTest {
    @Autowired
    UserMapper userMapper;
@Autowired
    UserService userService;
    @Test
    void test() {
//        userMapper.selectList(null).forEach(user -> System.out.println(user));
;
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username","cr");
        User user = userService.getOne(userQueryWrapper);
        System.out.println(user);
    }
    @Test
    void addUser() {
        User user = new User();
        user.setUsername("cr");
        user.setPassword("$2a$10$W1S143n/eZOZMs5C7Y.hV.uAj6OYQjt1DeDhYHGQG6RTB6sPz7k1u");
        userMapper.insert(user);
    }

    @Test
    void getMenusByUserID() {
        List<Menu> allMenusByUserID = userMapper.getAllMenusByUserID(1);
        allMenusByUserID.forEach(item-> System.out.println(item));
    }
}
