package com.juzi.usercenter.constant;

/**
 * 用户常量
 *
 * @author codejuzi
 */
public interface UserConstants {

    // region 校验逻辑

    /**
     * 密码加密盐值
     */
    String SALT = "codejuzi";

    /**
     * 账号最大长度
     */
    int ACC_MAX_LEN = 8;

    /**
     * 密码最短长度
     */
    int PWD_MIN_LEN = 8;
    // endregion

    // region 业务

    /**
     * 默认用户名前缀
     */
    String DEFAULT_UNAME_PREFIX = "User_";

    /**
     * 默认用户名后缀长度
     */
    int DEFAULT_UNAME_SUFFIX_LEN = 10;

    /**
     * 默认头像图片地址
     */
    String DEFAULT_AVATAR_URL = "https://photo.16pic.com/00/53/26/16pic_5326745_b.jpg";

    /**
     * 用户登录态键
     */
    String USER_LOGIN_STATUS_KEY = "user_login";
    // endregion

    // region 用户角色
    /**
     * 普通用户
     */
    Integer USER = 0;

    /**
     * 管理员
     */
    Integer ADMIN = 1;

    /**
     * 被封号的用户
     */
    Integer BANNED = 2;
    // endregion
}
