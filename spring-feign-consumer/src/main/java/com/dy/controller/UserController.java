package com.dy.controller;

import com.alibaba.fastjson.JSON;
import com.dy.service.UserService;
import com.dy.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
@Slf4j
public class UserController {
    @Resource
    UserService userService;

    @RequestMapping("info")
    String info() {
        return userService.info();
    }

    @RequestMapping("userInfo")
    User userInfo(User user){
        log.info(JSON.toJSONString(user));
        return userService.userInfo(user);
    }

    @RequestMapping("get")
    User get(String name,Integer age) {
        return userService.get(name,age);
    }

    @RequestMapping("get/{id}")
    User rest(@PathVariable("id") String id) {
        return userService.rest(id);
    }

}
