package com.cr.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cr.config.security.service.CustomUserDetailsService;
import com.cr.pojo.Menu;
import com.cr.service.MenuService;
import com.cr.service.UserService;
import com.cr.utils.Result;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cr
 * @since 2023-06-05
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    MenuService menuService;
    List<Menu> treeMenuList;
    @RequestMapping("/getMenuList")
    Result getMenuList(@RequestParam(value = "id",required = true) int id) {
        treeMenuList=new LinkedList<>();
        System.out.println("前端开始获取动态菜单数据");
        //得到一级菜单
       try {
           List<Menu> directory = menuService.getDirectory(id);
           for (Menu menu:directory) {
               digui(menu);
               treeMenuList.add(menu);
           }
           return Result.ok(treeMenuList).message("获取动态菜单数据成功");
       } catch (Exception e) {
           e.printStackTrace();
           return Result.ok(treeMenuList).message("获取动态菜单数据失败");

       }
    }

    private void digui(Menu menu) {
        List<Menu> currentIdChildren = menuService.getCurrentIdChildren(menu.getId());
        if(!ObjectUtils.isEmpty(currentIdChildren)) {//递归终止条件就是currentIdChildren为空
            for(Menu child:currentIdChildren) {
                digui(child);
                menu.getChildren().add(child);
            }
        }
    }


}

