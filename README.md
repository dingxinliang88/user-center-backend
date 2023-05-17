# 用户中心（后端）

用户表设计

| 字段           | 数据类型     | 说明                              |
|--------------|----------|---------------------------------|
| id           | big int  | 主键、自增、非空                        |
| userName     | varchar  | 用户昵称，代码层面设置默认值，可不填              |
| userAccount  | varchar  | 登录账号，非空，最大为8位                   |
| userPassword | varchar  | 密码，非空，以加密的方式存入数据库，用户填写的密码不得少于8位 |
| userAvatar   | varchar  | 用户头像图片地址，代码层面给默认值               |
| gender       | tiny int | 性别：1 - 男，0 - 女                  |
| phone        | varchar  | 手机号，允许为空                        |
| email        | varchar  | 邮箱。允许为空                         |
| userRole     | tiny int | 用户角色，0-普通用户，1-管理员，2-被封号的用户      |
| createTime   | datetime | 创建时间，默认为当前时间                    |
| updateTime   | datetime | 更新时间，变化时修改为当前时间                 |
| isDelete     | tiny int | 是否删除， 0 - 未删除，1 - 删除            |



## 如何上线?

1. 打包项目

	```sh
	mvn clean package
	```

2. 将Dockerfile和docker-compose.yml以及打包后的jar包一起上传至服务器上的同级目录

3. 构建镜像

	```sh
	docker build -t user-center-backend:0.0.1 .
	```

4. 创建my.cnf，位置：/app/mysql/conf/my.cnf，可以根据的自己的需求更改，同时需要更改docker-compose.yml内部的内容

	```ini
	[client]
	default_character_set=utf8
	[mysqld]
	collation_server = utf8_general_ci
	character_set_server = utf8
	```

5. 启动镜像

	```sh
	docker-compose up -d
	```

6. 进入mysql容器

	```sh
	docker exec -it contaner-id(mysql) /bin/bash
	```

	创建数据库和表（建表语句在`sql/create_table.sql`内，模拟数据在`sql/user.sql`内，密码字段都是加密后的，加密前都是12345678）

7. 启动[前端](https://github.com/dingxinliang88/user-center-frontend)访问即可







