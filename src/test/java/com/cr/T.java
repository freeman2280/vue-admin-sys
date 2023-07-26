package com.cr;

import com.cr.pojo.Menu;
import com.cr.pojo.User;
import com.cr.service.MenuService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class T {

    @Autowired
    MenuService menuService;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Value("${url.white_list}")
    List<String> list;
@Value("${server.servlet.context-path}")
String string;
    @Test
    void s() throws JsonProcessingException {
        List<Menu> currentIdChildren = menuService.getCurrentIdChildren(1);
        currentIdChildren.forEach(i-> System.out.println(i.getId()));
    }
    @Test
    void ss() throws JsonProcessingException {
        String a=new String("1");
      change(a);
        System.out.println(a);
    }

    private void change(String id) {

        id="4444";
    }

}
