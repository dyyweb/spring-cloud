package com.dy.controller;

import com.dy.domain.User;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * com.dy.controller
 *
 * @author DengYang
 * @date 2018/6/12 16:43
 * @since JDK1.8.0_161
 */
@RestController
@EnableAutoConfiguration
@RequestMapping("user")
public class UserController{

    @RequestMapping("info")
    String info() {
        return "info - Hello World 我是邓洋的spring-boot!";
    }
    @RequestMapping("userInfo")
    String userInfo(User user) {
        return "Hello World 我是"+user.getName()+"的spring-boot! age="+user.getAge();
    }

    @RequestMapping("get")
    User get(String name ,Integer age) {
        return User.builder().name(name).age(age).date(new Date()).build();
    }

    @RequestMapping("get/{id}")
    User rest(@PathVariable("id") String id) {
        return User.builder().name(id).age(80).date(new Date()).build();
    }


}
