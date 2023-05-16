package com.juzi.usercenter.model.enums;

import java.util.Objects;

/**
 * 用户角色枚举类
 *
 * @author codejuzi
 */
public enum UserRoleEnums {
    USER("普通用户", 0),
    ADMIN("管理员", 1),
    BANNED("被封号", 2);

    private final String description;

    private final Integer userRole;

    UserRoleEnums(String description, Integer userRole) {
        this.description = description;
        this.userRole = userRole;
    }

    public Integer getUserRole() {
        return userRole;
    }

    public String getDescription() {
        return description;
    }

    /**
     * 根据userRole(0, 1, 2)获取枚举值
     *
     * @param userRole @Link com.juzi.usercenter.constant.UserConstants
     * @return 枚举值
     */
    public static UserRoleEnums getEnumByUserRole(Integer userRole) {
        if (Objects.isNull(userRole)) {
            return null;
        }
        for (UserRoleEnums userRoleEnums : UserRoleEnums.values()) {
            if (userRoleEnums.getUserRole().equals(userRole)) {
                return userRoleEnums;
            }
        }
        return null;
    }
}
