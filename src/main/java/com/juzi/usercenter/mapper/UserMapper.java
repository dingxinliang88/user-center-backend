package com.juzi.usercenter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.juzi.usercenter.model.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * User Mapper
 *
 * @author codejuzi
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}