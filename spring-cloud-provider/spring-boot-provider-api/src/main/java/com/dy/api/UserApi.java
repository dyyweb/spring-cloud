package com.dy.api;

import com.dy.domain.User;
import org.springframework.web.bind.annotation.*;

/**
 * ${todo}
 *
 * @Package com.dy.api
 * @author: DY
 * @date: 2018-06-22 15:15
 * @since: jdk1.8
 */

@RequestMapping("provider")
public interface UserApi {

    @RequestMapping("info")
    String info() ;

    @RequestMapping(value = "userInfo", method = RequestMethod.POST)
    User userInfo(@RequestBody User user);

    @RequestMapping("get")
    User get(@RequestParam("name") String name , @RequestParam("age") Integer age);

    @RequestMapping("get/{id}")
    User rest(@PathVariable("id") String id);
}
