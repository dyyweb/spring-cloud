package com.dy.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * com.dy.controller
 *
 * @author dengy
 * @date 2018-08-2018/8/29
 * @since jdk1.6
 */
@RestController
@RequestMapping("local")
public class Local {

    @RequestMapping("hello")
    public String hello() {
        return "我是本地方法信息";
    }
}
