package com.cr.service;

import com.cr.pojo.Menu;
import com.cr.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cr
 * @since 2023-06-05
 */
public interface UserService extends IService<User> {
    List<Menu> getAllMenusByUserID(int id);
}
