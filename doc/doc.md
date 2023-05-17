## 用户表设计

| 字段         | 数据类型 | 说明                                                         |
| ------------ | -------- | ------------------------------------------------------------ |
| id           | big int  | 主键、自增、非空                                             |
| userName     | varchar  | 用户昵称，代码层面设置默认值，可不填                         |
| userAccount  | varchar  | 登录账号，非空，最大为8位                                    |
| userPassword | varchar  | 密码，非空，以加密的方式存入数据库，用户填写的密码不得少于8位 |
| userAvatar   | varchar  | 用户头像图片地址，代码层面给默认值                           |
| gender       | tiny int | 性别：1 - 男，0 - 女                                         |
| phone        | varchar  | 手机号，允许为空                                             |
| email        | varchar  | 邮箱。允许为空                                               |
| userRole     | tiny int | 用户角色，0-普通用户，1-管理员，2-被封号的用户               |
| createTime   | datetime | 创建时间，默认为当前时间                                     |
| updateTime   | datetime | 更新时间，变化时修改为当前时间                               |
| isDelete     | tiny int | 是否删除， 0 - 未删除，1 - 删除                              |



```sql
CREATE TABLE `user` (
    id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL COMMENT '主键、自增、非空',
    userName VARCHAR(255) NULL COMMENT '用户昵称，代码层面设置默认值，可不填',
    userAccount VARCHAR(8) NOT NULL COMMENT '登录账号，非空，最大为8位',
    userPassword VARCHAR(255) NOT NULL COMMENT '密码，非空，以加密的方式存入数据库，用户填写的密码不得少于8位',
    userAvatar VARCHAR(255) NULL COMMENT '用户头像图片地址，代码层面给默认值',
    gender TINYINT NULL COMMENT '性别：1 - 男，0 - 女',
    phone VARCHAR(11) NULL COMMENT '手机号，允许为空',
    email VARCHAR(255) NULL COMMENT '邮箱。允许为空',
    userRole TINYINT DEFAULT 0 NULL COMMENT '用户角色，0-普通用户，1-管理员，2-被封号的用户',
    createTime DATETIME DEFAULT CURRENT_TIMESTAMP NULL COMMENT '创建时间，默认为当前时间',
    updateTime DATETIME DEFAULT CURRENT_TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间，变化时修改为当前时间',
    isDelete TINYINT DEFAULT 0 NULL COMMENT '是否删除，0 - 未删除，1 - 删除'
);

```



## 注册逻辑

1. 用户输入账号和密码（输入两次）
2. 校验用户的账号、密码、校验密码是否符合要求
	1. 非空
	2. 账号长度 <=  8
	3. 密码长度 >= 8
	4. 账号不能重复
	5. 账号不包含特殊字符
	6. 密码和校验密码相同
3. 对密码加密
4. 插入数据



## 登录逻辑

1. 校验用户账号和密码是否合法
	1. 非空
	2. 账号长度 <=  8
	3. 密码长度 >= 8
	4. 账号不包含特殊字符
2. 校验密码是否输入正确，要和数据库中的密文密码对比
3. 用户信息脱敏，隐藏敏感信息，防止数据库中的敏感字段泄露
4. 记录用户登录态
5. 返回脱敏后的用户信息



# TODO

- 前端注册加入校验码
- 登录注册加锁
- SSO单点登录
- 查询优化，允许多字段查询
- 前端用户编辑，上传图片
- 获取当前登录用户需要用缓存

	
