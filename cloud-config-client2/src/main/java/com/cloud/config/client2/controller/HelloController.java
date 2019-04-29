package com.cloud.config.client2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Dong.L
 * @Create: 2019-01-15 17:52
 * @Description: java类描述
 */
@RestController
@RefreshScope // 动态刷新
public class HelloController {
    @Autowired
    private Environment environment;

    /**
     * git配置文件里的key
     */
    @Value("${mytest}")
    String mytest;

    @RequestMapping(value = "/hi")
    public String hi() {
        return mytest;
    }

    @RequestMapping("/hello")
    public String hello(){
        return environment.getProperty("mytest", "未定义");
    }
}
