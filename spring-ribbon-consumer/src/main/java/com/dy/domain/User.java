package com.dy.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * com.dy.domain
 *
 * @author DengYang
 * @date 2018/6/15 10:49
 * @since JDK1.8.0_161
 */
@Getter
@Setter
@Builder
public class User {
    private String name;
    private Integer age;
    private Date date;

}
