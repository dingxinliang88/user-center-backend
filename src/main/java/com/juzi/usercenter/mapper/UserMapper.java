package com.juzi.usercenter.mapper;

import com.juzi.usercenter.model.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author codejuzi
 * @description 针对表【user】的数据库操作Mapper
 * @createDate 2023-05-15 20:18:05
 * @Entity com.juzi.usercenter.model.entity.User
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




