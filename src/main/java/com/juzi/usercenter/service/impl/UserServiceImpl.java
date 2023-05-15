package com.juzi.usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.juzi.usercenter.common.StatusCode;
import com.juzi.usercenter.model.dto.user.UserRegisterRequest;
import com.juzi.usercenter.model.entity.User;
import com.juzi.usercenter.service.UserService;
import com.juzi.usercenter.mapper.UserMapper;
import com.juzi.usercenter.utils.ThrowUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author codejuzi
 * @description 针对表【user】的数据库操作Service实现
 * @createDate 2023-05-15 20:18:05
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    private static final String SALT = "codejuzi";

    @Override
    public Long userRegister(UserRegisterRequest userRegisterRequest) {
        // 校验
        ThrowUtils.throwIf(Objects.isNull(userRegisterRequest), StatusCode.PARAMS_ERROR, "注册参数不能为空！");
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkedPassword = userRegisterRequest.getCheckedPassword();
        // 1. 非空
        ThrowUtils.throwIf(StringUtils.isAnyBlank(userAccount, userPassword, checkedPassword),
                StatusCode.PARAMS_ERROR, "注册参数不能为空！");
        // 2. 账号长度 <= 8
        ThrowUtils.throwIf(userAccount.length() > 8, StatusCode.PARAMS_ERROR, "账号不能超过8位！");
        // 3. 密码长度 >= 8
        ThrowUtils.throwIf(userPassword.length() < 8 || checkedPassword.length() < 8,
                StatusCode.PARAMS_ERROR, "密码长度不能小于8位！");
        // 4. 密码和校验密码相同
        ThrowUtils.throwIf(!userPassword.equals(checkedPassword), StatusCode.PARAMS_ERROR, "两次输入密码不一致！");
        // 5. 账号不包含特殊字符
        String accountValidPattern = "^[a-zA-Z0-9]+$";
        Matcher matcher = Pattern.compile(accountValidPattern).matcher(userAccount);
        ThrowUtils.throwIf(!matcher.find(), StatusCode.PARAMS_ERROR, "账号不能包含特殊字符！");
        // 6. 账号不能重复
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getUserAccount, userAccount);
        User user = this.getOne(userLambdaQueryWrapper);
        ThrowUtils.throwIf(!Objects.isNull(user), StatusCode.PARAMS_ERROR, "该账号已经存在！");

        // 加密密码
        String encryptedPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes(StandardCharsets.UTF_8));

        // 封装数据
        User newUser = new User();
        newUser.setUserName("User_" + RandomStringUtils.randomAlphabetic(10));
        newUser.setUserAccount(userAccount);
        newUser.setUserPassword(encryptedPassword);
        newUser.setUserAvatar("https://photo.16pic.com/00/53/26/16pic_5326745_b.jpg");
        // 插入数据
        boolean result = this.save(newUser);
        ThrowUtils.throwIf(!result, StatusCode.SYSTEM_ERROR, "插入数据失败");
        // 返回新用户id
        return newUser.getId();
    }
}




