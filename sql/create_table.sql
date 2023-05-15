create database user_center;

use user_center;

DROP TABLE IF EXISTS user;

CREATE TABLE `user`
(
    id           BIGINT PRIMARY KEY AUTO_INCREMENT  NOT NULL COMMENT '主键、自增、非空',
    userName     VARCHAR(255)                       NULL COMMENT '用户昵称，代码层面设置默认值，可不填',
    userAccount  VARCHAR(8)                         NOT NULL COMMENT '登录账号，非空，最大为8位',
    userPassword VARCHAR(255)                       NOT NULL COMMENT '密码，非空，以加密的方式存入数据库，用户填写的密码不得少于8位',
    userAvatar   VARCHAR(255)                       NULL COMMENT '用户头像图片地址，代码层面给默认值',
    gender       TINYINT                            NULL COMMENT '性别：1-男，0-女',
    phone        VARCHAR(11)                        NULL COMMENT '手机号，允许为空',
    email        VARCHAR(255)                       NULL COMMENT '邮箱。允许为空',
    userRole     TINYINT  DEFAULT 0                 NULL COMMENT '用户角色，0-普通用户，1-管理员，2-被封号的用户',
    createTime   DATETIME DEFAULT CURRENT_TIMESTAMP NULL COMMENT '创建时间，默认为当前时间',
    updateTime   DATETIME DEFAULT CURRENT_TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间，变化时修改为当前时间',
    isDelete     TINYINT  DEFAULT 0                 NULL COMMENT '是否删除，0-未删除，1-删除'
);
