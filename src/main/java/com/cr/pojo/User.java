package com.cr.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
@TableName("sys_user")
public class User implements Serializable, UserDetails {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
      @TableId(value = "id", type = IdType.AUTO)
    private int id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 城市
     */
    private String city;

    /**
     * 被创建时间
     */
    @TableField(fill = FieldFill.INSERT)//自动填充
    private Date created;

    /**
     * 更新资料时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)//自动填充
    private Date updated;

    /**
     * 上次登录时间
     */
    private Date lastLogin;

    /**
     * 逻辑删除，默认为0,1代表删除
     */
    @TableLogic
    private Integer isDeleted;

    /**
     * 帐户是否过期(1-未过期，0-已过期)
     */
    private boolean isAccountNonExpired=true;

    /**
     * 帐户是否被锁定(1-未过期，0-已过期)
     */
    private boolean isAccountNonLocked=true;

    /**
     * 密码是否过期(1-未过期，0-已过期)
     */
    private boolean isCredentialsNonExpired=true;

    /**
     * 帐户是否可用(1-可用，0-禁用)
     */
    private boolean isEnabled=true;

    /**
     * 权限列表
     */
    @TableField(exist = false)
    List<String> perms;
    @TableField(exist = false)
    Collection<MyGrantedAuthority> authorities;

    /**
     * 用户权限所有信息
     */
    @TableField(exist = false)
    List<Menu> menuList;


}


