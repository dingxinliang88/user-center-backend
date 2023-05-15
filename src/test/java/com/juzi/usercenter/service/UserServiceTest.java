package com.juzi.usercenter.service;

import com.juzi.usercenter.model.dto.user.UserRegisterRequest;
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

    //    @Test
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

    //    @Test
    void testQueryUser() {
        Long userId = 1L;
        User user = userService.getById(userId);
        assertNotNull(user);
        System.out.println("user = " + user);
    }

    //    @Test
    void userRegisterParamError() {
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        // 为空
        userService.userRegister(userRegisterRequest);
    }

    //    @Test
    void accountTooLong() {
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        // 账号长度过长
        userRegisterRequest.setUserAccount("codejuzicodejuzi");
        userRegisterRequest.setUserPassword("12345678");
        userRegisterRequest.setCheckedPassword("12345678");
        userService.userRegister(userRegisterRequest);
    }

    //    @Test
    void passwordTooShort() {
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        // 密码过短
        userRegisterRequest.setUserAccount("codejuzi");
        userRegisterRequest.setUserPassword("12345");
        userRegisterRequest.setCheckedPassword("12345678");
        userService.userRegister(userRegisterRequest);
    }

    //    @Test
    void checkedPasswordTooShort() {
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        // 校验密码过短
        userRegisterRequest.setUserAccount("codejuzi");
        userRegisterRequest.setUserPassword("12345678");
        userRegisterRequest.setCheckedPassword("12345");
        userService.userRegister(userRegisterRequest);
    }

    //    @Test
    void checkedPwdNotEqualsPwd() {
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        // 两次密码不一致
        userRegisterRequest.setUserAccount("codejuzi");
        userRegisterRequest.setUserPassword("12345678");
        userRegisterRequest.setCheckedPassword("123456789");
        userService.userRegister(userRegisterRequest);
    }

    //    @Test
    void accountPatternInvalid() {
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        // 账号包含特殊字符
        userRegisterRequest.setUserAccount("juzi ");
        userRegisterRequest.setUserPassword("12345678");
        userRegisterRequest.setCheckedPassword("12345678");
        userService.userRegister(userRegisterRequest);
    }


    //    @Test
    void accountExist() {
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        // 账号存在
        userRegisterRequest.setUserAccount("Test1");
        userRegisterRequest.setUserPassword("12345678");
        userRegisterRequest.setCheckedPassword("12345678");
        userService.userRegister(userRegisterRequest);
    }

    @Test
    void registerSuccess() {
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        // 正常插入
        userRegisterRequest.setUserAccount("codejuzi");
        userRegisterRequest.setUserPassword("12345678");
        userRegisterRequest.setCheckedPassword("12345678");
        Long userId = userService.userRegister(userRegisterRequest);
        System.out.println("userId = " + userId);
    }


}