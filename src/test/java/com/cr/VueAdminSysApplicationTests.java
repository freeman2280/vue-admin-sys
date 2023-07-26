package com.cr;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.ObjectUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import javax.sound.midi.Soundbank;
import java.util.List;

@SpringBootTest
class VueAdminSysApplicationTests {
//
    @Value("${url.white_list}")
    List<String> list;
    @Value("${url.o}")
    String string;
   @Autowired
    Environment environment;
    @Test
    void contextLoads() throws Exception{
        System.out.println(string);
//        String[] white_list = environment.getProperty("w",String[].class);
//        if(!ObjectUtils.isEmpty(white_list)) {
//            System.out.println(white_list.length);
//        }
//        else {
//            System.out.println("找不到");
//            System.out.println(environment.getProperty("o"));
//            System.out.println(list.size());
//        }
        System.out.println(list.size());
    }

}
