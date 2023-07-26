package com.cr.mapper;

import com.cr.pojo.Menu;
import com.cr.pojo.User;
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
public interface UserMapper extends BaseMapper<User> {
    List<Menu> getAllMenusByUserID(int id);

}
