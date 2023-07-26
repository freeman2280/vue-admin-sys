package com.cr.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author cr
 * @since 2023-06-05
 */
@Data
@Accessors(chain = true)
@TableName("sys_menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
      @TableId(value = "id", type = IdType.AUTO)
    private int id;

    /**
     * 父菜单ID，一级菜单为0
     */
    private Long parentId;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单URL
     */
    private String path;

    /**
     * 授权(多个用逗号分隔，如：user:list,user:create)
     */
    private String perms;

    /**
     * 路由组件
     */
    private String component;

    /**
     * 类型     0：目录   1：菜单   2：按钮
     */
    private Integer type;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 排序
     */
    @TableField("orderNum")
    private Integer ordernum;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)//自动填充
    private Date created;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)//自动填充
    private Date updated;

    /**
     * 状态，默认为0,1代表禁用
     */
    private Integer statu;

    /**
     * 逻辑删除，默认为0,1代表删除
     */
    @TableLogic
    private Integer isDeleted;

    /**
     * 备注
     */
    private String remark;

    @TableField(exist = false)
    private List<Menu> children=new ArrayList<>();

}
