package com.juzi.usercenter.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @TableName user
 */
@TableName(value = "user")
@Data
public class User implements Serializable {
    /**
     * 主键、自增、非空
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户昵称，代码层面设置默认值，可不填
     */
    private String userName;

    /**
     * 登录账号，非空，最大为8位
     */
    private String userAccount;

    /**
     * 密码，非空，以加密的方式存入数据库，用户填写的密码不得少于8位
     */
    private String userPassword;

    /**
     * 用户头像图片地址，代码层面给默认值
     */
    private String userAvatar;

    /**
     * 性别：1-男，0-女
     */
    private Integer gender;

    /**
     * 手机号，允许为空
     */
    private String phone;

    /**
     * 邮箱。允许为空
     */
    private String email;

    /**
     * 用户角色，0-普通用户，1-管理员，2-被封号的用户
     */
    private Integer userRole;

    /**
     * 创建时间，默认为当前时间
     */
    private Date createTime;

    /**
     * 更新时间，变化时修改为当前时间
     */
    private Date updateTime;

    /**
     * 是否删除，0-未删除，1-删除
     */
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}