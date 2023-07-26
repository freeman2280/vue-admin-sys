package com.cr.service.impl;

import com.cr.pojo.Menu;
import com.cr.pojo.User;
import com.cr.mapper.UserMapper;
import com.cr.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cr
 * @since 2023-06-05
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
@Autowired
UserMapper userMapper;
    @Override
    public List<Menu> getAllMenusByUserID(int id) {
        return userMapper.getAllMenusByUserID(id);
    }
}
