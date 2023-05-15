package com.juzi.usercenter.controller;

import com.juzi.usercenter.common.BaseResponse;
import com.juzi.usercenter.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author codejuzi
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/test")
    public BaseResponse<String> testLogAspect() {
        String res = "log aspect~~";
        return ResultUtils.success(res);
    }
}
