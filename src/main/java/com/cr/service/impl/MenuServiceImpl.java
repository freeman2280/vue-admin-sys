package com.cr.service.impl;

import com.cr.pojo.Menu;
import com.cr.mapper.MenuMapper;
import com.cr.service.MenuService;
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
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
@Autowired
MenuMapper menuMapper;
    @Override
    public List<Menu> getCurrentIdChildren(int id) {
        return menuMapper.getCurrentIdChildren(id);
    }

    @Override
    public List<Menu> getDirectory(int id) {
        return menuMapper.getDirectory(id);
    }
}
