package com.dy.controller;

import com.dy.domain.User;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
@RequestMapping("ribbon")
public class TestRibbonController {

    @Resource
    private RestTemplate restTemplate;

    @RequestMapping("test")
    String test() {
        return restTemplate.getForEntity("http://SPRING-BOOT-PROVIDER/provider/info", String.class).getBody();
    }

    @RequestMapping("get")
    User get() {
        return User.builder().name("邓洋").age(80).date(new Date()).build();
    }

    @RequestMapping("get/{id}")
    User rest(@PathVariable("id") String id) {
        return User.builder().name(id).age(80).date(new Date()).build();
    }

}
