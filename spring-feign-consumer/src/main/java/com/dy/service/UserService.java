package com.dy.service;

import com.dy.api.UserApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * ${todo}
 *
 * @Package com.dy.controller.service
 * @author: DY
 * @date: 2018-06-22 15:32
 * @since: jdk1.8
 */
@FeignClient("spring-boot-provider")
public interface UserService extends UserApi {
}
