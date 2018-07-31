package com.dy.controller;

import com.alibaba.fastjson.JSON;
import com.dy.service.UserService;
import com.dy.domain.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.context.config.annotation.RefreshScope;
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
@RefreshScope // 使用该注解的类，会在接到SpringCloud配置中心配置刷新的时候，自动将新的配置更新到该类对应的字段中。
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

    @RequestMapping("get/{id}")
    User rest(@PathVariable("id") String id) {
        return userService.rest(id);
    }

    /**
     * 服务降级和熔断
     * @param name
     * @param age
     * @return
     */
    @RequestMapping("get")
    @HystrixCommand(fallbackMethod="getFallback") // 如果当前调用的get()方法出现了错误，则执行fallback
    User get(String name,Integer age) {
        return userService.get(name,age);
    }

    /**
     * 服务降级处理回调方法保持get()参数一致
     *
     * 还有一种方式，@FeignClient就是接口中指定回调的类和方法(个人感觉降级是由客户端处理的，不应该侵入服务端代码)
     * 当然还有更高级和深入用法，请参考官网
     * @param name
     * @param age
     * @return
     */
    public User getFallback(String name,Integer age) {
        //增加Throwable参数,可以获取异常信息
        User vo = new User("后台服务挂了,我已经将其降级处理[Hystrix]",0,new Date()) ;
        return vo ;
    }

}
