package com.cr.mapper;

import com.cr.pojo.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cr
 * @since 2023-06-05
 */
public interface MenuMapper extends BaseMapper<Menu> {
    /**
     * 根据user的id获取到其对应权限的所有目录（一级目录）
     * @param id
     * @return
     */
    List<Menu> getDirectory(int id);

    /**
     * 根据当前menu的id的到其所有子孩子菜单
     * @param id
     * @return
     */
    List<Menu> getCurrentIdChildren(int id);
}
