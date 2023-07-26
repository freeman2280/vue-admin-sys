package com.cr.service;

import com.cr.pojo.Menu;
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
public interface MenuService extends IService<Menu> {
    /**
     * 根据当前menu的id的到其所有子孩子菜单
     * @param id
     * @return
     */
    List<Menu> getCurrentIdChildren(int id);
    /**
     * 根据user的id获取到其对应权限的所有目录（一级目录）
     * @param id
     * @return
     */
    List<Menu> getDirectory(int id);
}
