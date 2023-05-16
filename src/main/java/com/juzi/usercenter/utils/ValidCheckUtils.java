package com.juzi.usercenter.utils;

import com.juzi.usercenter.common.StatusCode;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.juzi.usercenter.constant.UserConstants.ACC_MAX_LEN;
import static com.juzi.usercenter.constant.UserConstants.PWD_MIN_LEN;

/**
 * 合法性校验工具类
 *
 * @author codejuzi
 */
public class ValidCheckUtils {

    public static void checkLoginParams(String userAccount, String userPassword) {
        // 1. 非空
        ThrowUtils.throwIf(StringUtils.isAnyBlank(userAccount, userPassword),
                StatusCode.PARAMS_ERROR, "参数不能为空！");
        // 2. 账号长度 <= 8
        ThrowUtils.throwIf(userAccount.length() > ACC_MAX_LEN, StatusCode.PARAMS_ERROR, "账号不能超过8位！");
        // 3. 密码长度 >= 8
        ThrowUtils.throwIf(userPassword.length() < PWD_MIN_LEN, StatusCode.PARAMS_ERROR, "密码长度不能小于8位！");
        // 4. 账号不包含特殊字符
        String accountValidPattern = "^[a-zA-Z0-9]+$";
        Matcher matcher = Pattern.compile(accountValidPattern).matcher(userAccount);
        ThrowUtils.throwIf(!matcher.find(), StatusCode.PARAMS_ERROR, "账号不能包含特殊字符！");
    }

    public static void checkRegisterParams(String userAccount, String userPassword, String checkedPassword) {
        // 复用
        checkLoginParams(userAccount, userPassword);

        // 5.校验密码长度 >= 8
        ThrowUtils.throwIf(checkedPassword.length() < PWD_MIN_LEN, StatusCode.PARAMS_ERROR, "校验密码长度不能小于8位！");
        // 6.密码 == 校验密码
        ThrowUtils.throwIf(!userPassword.equals(checkedPassword), StatusCode.PARAMS_ERROR, "两次输入密码不一致！");

    }
}
