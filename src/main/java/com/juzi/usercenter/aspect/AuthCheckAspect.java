package com.juzi.usercenter.aspect;

import com.juzi.usercenter.annotations.AuthCheck;
import com.juzi.usercenter.common.StatusCode;
import com.juzi.usercenter.constant.UserConstants;
import com.juzi.usercenter.model.enums.UserRoleEnums;
import com.juzi.usercenter.model.vo.user.UserVO;
import com.juzi.usercenter.service.UserService;
import com.juzi.usercenter.utils.ThrowUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author codejuzi
 */
@Slf4j
@Aspect
@Component
public class AuthCheckAspect {

    @Resource
    private UserService userService;

    @Around("@annotation(authCheck)")
    public Object doAuthCheck(ProceedingJoinPoint joinPoint, AuthCheck authCheck) throws Throwable {
        String mustRole = authCheck.mustRole();
        // 不需要特殊权限，直接放行
        if (StringUtils.isBlank(mustRole)) {
            return joinPoint.proceed();
        }
        // 需要当前角色有特殊权限
        UserRoleEnums mustRoleEnum = UserRoleEnums.getEnumByDesc(mustRole);
        ThrowUtils.throwIf(Objects.isNull(mustRoleEnum), StatusCode.NO_AUTH_ERROR, "无权访问！");

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = null;
        if (requestAttributes != null) {
            request = ((ServletRequestAttributes) requestAttributes).getRequest();
        }
        // 获取当前登录用户
        UserVO loginUser = userService.getLoginUser(request);
        // 获取用户角色及对应的枚举值
        Integer userRole = loginUser.getUserRole();
        UserRoleEnums userRoleEnum = UserRoleEnums.getEnumByUserRole(userRole);
        ThrowUtils.throwIf(Objects.isNull(userRoleEnum), StatusCode.NO_AUTH_ERROR, "当前角色无权访问");
        // 被封号的直接拒绝
        ThrowUtils.throwIf(UserRoleEnums.BANNED.equals(userRoleEnum), StatusCode.NO_AUTH_ERROR, "当前角色已被封号");
        // 必须有管理员权限
        ThrowUtils.throwIf(UserRoleEnums.ADMIN.equals(mustRoleEnum) && !UserConstants.ADMIN.equals(userRole),
                StatusCode.NO_AUTH_ERROR, "无管理员权限");
        return joinPoint.proceed();
    }
}
