package com.juzi.usercenter.common;

/**
 * 状态码
 *
 * @author codejuzi
 */
public enum StatusCode {
    SUCCESS(0, "success"),
    NOT_FOUND_ERROR(40400, "请求数据不存在"),
    NOT_LOGIN_ERROR(40500, "未登录"),
    PARAMS_ERROR(40000, "请求参数不合法"),
    FORBIDDEN(40300, "禁止访问"),
    NO_AUTH_ERROR(40100, "无权访问"),
    SYSTEM_ERROR(50000, "系统内部错误"),
    OPERATION_ERROR(50100, "操作错误");

    private final int code;

    private final String message;


    StatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
