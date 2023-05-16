package com.juzi.usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.juzi.usercenter.common.StatusCode;
import com.juzi.usercenter.model.dto.user.UserLoginRequest;
import com.juzi.usercenter.model.dto.user.UserRegisterRequest;
import com.juzi.usercenter.model.entity.User;
import com.juzi.usercenter.model.vo.user.UserVO;
import com.juzi.usercenter.service.UserService;
import com.juzi.usercenter.mapper.UserMapper;
import com.juzi.usercenter.utils.ThrowUtils;
import com.juzi.usercenter.utils.ValidCheckUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.juzi.usercenter.constant.UserConstants.*;

/**
 * @author codejuzi
 * @description 针对表【user】的数据库操作Service实现
 * @createDate 2023-05-15 20:18:05
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Override
    public Long userRegister(UserRegisterRequest userRegisterRequest) {
        // 校验
        ThrowUtils.throwIf(Objects.isNull(userRegisterRequest), StatusCode.PARAMS_ERROR, "注册参数不能为空！");
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkedPassword = userRegisterRequest.getCheckedPassword();
        ValidCheckUtils.checkRegisterParams(userAccount, userPassword, checkedPassword);
        // 6. 账号不能重复
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getUserAccount, userAccount);
        User user = this.getOne(userLambdaQueryWrapper);
        ThrowUtils.throwIf(!Objects.isNull(user), StatusCode.PARAMS_ERROR, "该账号已经存在！");

        // 加密密码
        String encryptedPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes(StandardCharsets.UTF_8));

        // 封装数据
        User newUser = new User();
        newUser.setUserName(DEFAULT_UNAME_PREFIX + RandomStringUtils.randomAlphabetic(DEFAULT_UNAME_SUFFIX_LEN));
        newUser.setUserAccount(userAccount);
        newUser.setUserPassword(encryptedPassword);
        newUser.setUserAvatar(DEFAULT_AVATAR_URL);
        // 插入数据
        boolean result = this.save(newUser);
        ThrowUtils.throwIf(!result, StatusCode.SYSTEM_ERROR, "插入数据失败");
        // 返回新用户id
        return newUser.getId();
    }

    @Override
    public UserVO userLogin(UserLoginRequest userLoginRequest, HttpServletRequest request) {
        // 校验
        ThrowUtils.throwIf(Objects.isNull(userLoginRequest), StatusCode.PARAMS_ERROR, "登录信息不能为空");
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        ValidCheckUtils.checkLoginParams(userAccount, userPassword);

        // 校验密码是否正确
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserAccount, userAccount);
        User userFromDb = this.getOne(queryWrapper);
        ThrowUtils.throwIf(Objects.isNull(userFromDb), StatusCode.NOT_FOUND_ERROR, "账号尚未注册！");
        String pwdFromDb = userFromDb.getUserPassword();
        String encryptedPwd = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes(StandardCharsets.UTF_8));
        ThrowUtils.throwIf(!pwdFromDb.equals(encryptedPwd), StatusCode.PARAMS_ERROR, "密码不正确！");

        // 用户信息脱敏
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userFromDb, userVO);

        // 保存用户登录态
        HttpSession session = request.getSession();
        session.setAttribute(USER_LOGIN_STATUS_KEY, userVO);

        // 返回脱敏后的用户信息
        return userVO;
    }

    @Override
    public UserVO getLoginUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserVO loginUserVO = (UserVO) session.getAttribute(USER_LOGIN_STATUS_KEY);
        ThrowUtils.throwIf(Objects.isNull(loginUserVO), StatusCode.NOT_LOGIN_ERROR, "当前状态未登录");
        return loginUserVO;
    }
}




