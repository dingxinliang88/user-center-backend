package com.juzi.usercenter.service;

import com.juzi.usercenter.model.dto.user.UserRegisterRequest;
import com.juzi.usercenter.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author codejuzi
 * @description 针对表【user】的数据库操作Service
 * @createDate 2023-05-15 20:18:05
 */
public interface UserService extends IService<User> {

    /**
     * 用户注册
     *
     * @param userRegisterRequest 用户注册请求参数封装体
     * @return 新注册用户id
     */
    Long userRegister(UserRegisterRequest userRegisterRequest);
}
