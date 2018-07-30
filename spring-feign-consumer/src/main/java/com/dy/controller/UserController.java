package com.dy.controller;

import com.alibaba.fastjson.JSON;
import com.dy.service.UserService;
import com.dy.domain.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
@Slf4j
public class UserController {
    @Value("${spring.cloud.name}")
    String name;
    @Value("${spring.cloud.version}")
    String version;

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
    @HystrixCommand(fallbackMethod="getFallback")    // 如果当前调用的get()方法出现了错误，则执行fallback
    User get(String name,Integer age) {
        return userService.get(name,age);
    }

    @RequestMapping("get/{id}")
    User rest(@PathVariable("id") String id) {
        return userService.rest(id);
    }

    public User getFallback(@PathVariable("id") long id) {
        User vo = new User("【ERROR】Microcloud-Dept-Hystrix",0,new Date()) ;
        return vo ;
    }

}
