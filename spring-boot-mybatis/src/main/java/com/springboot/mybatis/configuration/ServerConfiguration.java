package com.springboot.mybatis.configuration;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Dong.L
 * @Create: 2019-09-30 10:28
 * @Description: java类描述
 */
@Configuration
@ComponentScan(basePackages = "com.springboot.mybatis")
@MapperScan("com.springboot.mybatis.mapper")
public class ServerConfiguration {
}
