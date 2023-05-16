package com.juzi.usercenter.model.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户登录请求体
 *
 * @author codejuzi
 */
@Data
public class UserLoginRequest implements Serializable {

    private static final long serialVersionUID = -1737951199137575142L;

    /**
     * 登录账号，非空，最大为8位
     */
    private String userAccount;

    /**
     * 密码，非空，以加密的方式存入数据库，用户填写的密码不得少于8位
     */
    private String userPassword;
}
