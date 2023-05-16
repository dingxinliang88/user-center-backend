package com.juzi.usercenter.annotations;


import java.lang.annotation.*;

/**
 * @author codejuzi
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthCheck {

    /**
     * 必须包含的角色 ("admin", "user")
     *
     * @return user role
     */
    String mustRole();
}
