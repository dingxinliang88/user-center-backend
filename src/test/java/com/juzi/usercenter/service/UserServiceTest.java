package com.juzi.usercenter.service;

import com.juzi.usercenter.model.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author codejuzi
 */
@SpringBootTest
class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    void testAddUser() {
        // mock data
        User user = new User();
        user.setUserName("Test1");
        user.setUserAccount("test");
        user.setUserPassword("12345678");
        user.setUserAvatar("https://photo.16pic.com/00/53/26/16pic_5326745_b.jpg");
        user.setGender(0);
        user.setPhone("12345678");
        user.setEmail("12345678@test.com");

        // insert data
        boolean save = userService.save(user);
        assertTrue(save);
        System.out.println("user.getId() = " + user.getId());
    }

    @Test
    void testQueryUser() {
        Long userId = 1L;
        User user = userService.getById(userId);
        assertNotNull(user);
        System.out.println("user = " + user);
    }

}