package com.juzi.usercenter.mapper;

import com.juzi.usercenter.model.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author codejuzi
 */
@SpringBootTest
class UserMapperTest {
    @Resource
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }
}