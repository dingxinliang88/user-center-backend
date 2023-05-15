package com.juzi.usercenter.exception;

import com.juzi.usercenter.common.BaseResponse;
import com.juzi.usercenter.common.StatusCode;
import com.juzi.usercenter.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理类
 *
 * @author codejuzi
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = BusinessException.class)
    public BaseResponse<?> businessExceptionHandler(BusinessException businessException) {
        log.error("BusinessException: ", businessException);
        return ResultUtils.error(businessException.getCode(), businessException.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public BaseResponse<?> otherExceptionHandler(Exception e) {
        log.error("BusinessException: ", e);
        return ResultUtils.error(StatusCode.SYSTEM_ERROR);
    }
}
