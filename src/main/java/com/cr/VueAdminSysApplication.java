package com.cr;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cr.mapper")
public class VueAdminSysApplication {

    public static void main(String[] args) {
        SpringApplication.run(VueAdminSysApplication.class, args);
    }

}
