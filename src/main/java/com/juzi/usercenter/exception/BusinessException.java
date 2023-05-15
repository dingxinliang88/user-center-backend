package com.juzi.usercenter.exception;

import com.juzi.usercenter.common.StatusCode;

/**
 * 自定义业务异常类
 *
 * @author codejuzi
 */
public class BusinessException extends RuntimeException {

    /**
     * 状态码
     */
    private final int code;

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(StatusCode statusCode) {
        super(statusCode.getMessage());
        this.code = statusCode.getCode();
    }

    public BusinessException(StatusCode statusCode, String message) {
        super(message);
        this.code = statusCode.getCode();
    }

    public int getCode() {
        return code;
    }
}
