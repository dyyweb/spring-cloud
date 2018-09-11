package com.dy.controller;

import com.dy.api.UserApi;
import com.dy.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
@Slf4j
public class ProviderController implements UserApi {


    public String info() {
        return "info - Hello World 我是邓洋的spring-cloud-provider!";
    }

    public User userInfo(@RequestBody User user) {
        log.info("Hello World 我是" + user.getName() + "的spring-boot! age=" + user.getAge());
        return user;
    }


    public User get(String name, Integer age) {
        int i = 2/0;
        return User.builder().name(name).age(age).date(new Date()).build();
    }


    public User rest(@PathVariable("id") String id) {
        return User.builder().name(id).age(80).date(new Date()).build();
    }


}
