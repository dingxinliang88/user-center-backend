package com.juzi.usercenter.service;

import com.juzi.usercenter.model.dto.user.UserLoginRequest;
import com.juzi.usercenter.model.dto.user.UserRegisterRequest;
import com.juzi.usercenter.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.juzi.usercenter.model.vo.user.UserVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    /**
     * 用户登录
     *
     * @param userLoginRequest 用户登录请求信息封装体
     * @param request          http request
     * @return UserVO, 脱敏后的用户数据
     */
    UserVO userLogin(UserLoginRequest userLoginRequest, HttpServletRequest request);

    /**
     * 获取当前登录用户
     *
     * @param request http request
     * @return UserVO，登录态中的用户信息
     */
    UserVO getLoginUser(HttpServletRequest request);

    /**
     * 根据搜索关键词来模糊匹配userName、 userAccount查找用户。
     * 不传默认搜索所有用户，仅管理员可用
     *
     * @param searchText 搜索关键词
     * @return User全数据列表
     */
    List<User> queryUser(String searchText);

    /**
     * 根据userid 删除用户，仅管理员
     *
     * @param userId user id
     * @return true - 删除成功
     */
    Boolean deleteUserById(Long userId);

    /**
     * 用户登出
     *
     * @param request http request
     * @return true - 登出成功
     */
    Boolean userLogout(HttpServletRequest request);

}
