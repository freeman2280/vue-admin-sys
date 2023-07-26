package com.cr.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

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
@TableName("sys_role")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
      @TableId(value = "id", type = IdType.AUTO)
    private int id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色码
     */
    private String code;

    /**
     * 备注
     */
    private String remark;

    /**
     * 角色被创建时间
     */
    @TableField(fill = FieldFill.INSERT)//自动填充
    private Date created;

    /**
     * 角色更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)//自动填充
    private Date updated;

    /**
     * 状态 默认为0 1代表禁用
     */
    private Integer statu;

    /**
     * 是否删除 默认为0 1代表已经删除，逻辑删除
     */
    @TableLogic
    private Integer isDeleted;


}
