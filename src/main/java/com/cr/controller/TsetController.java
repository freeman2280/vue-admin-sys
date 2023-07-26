package com.cr.controller;

import com.cr.pojo.User;
import com.cr.service.UserService;
import com.cr.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TsetController {
    @Autowired
    UserService userService;
    @RequestMapping("/api/test/getAlluser")
    Result getAllUser() {
        List<User> list = userService.list();
        return  Result.ok(list).message("获取所有用户信息成功！");
    }
}
