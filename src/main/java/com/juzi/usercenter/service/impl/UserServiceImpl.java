package com.juzi.usercenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.juzi.usercenter.model.entity.User;
import com.juzi.usercenter.service.UserService;
import com.juzi.usercenter.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author codejuzi
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-05-15 20:18:05
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




