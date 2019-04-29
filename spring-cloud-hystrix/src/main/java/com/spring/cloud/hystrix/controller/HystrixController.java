package com.spring.cloud.hystrix.controller;

import com.spring.cloud.hystrix.service.HystrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Dong.L
 * @Create: 2019-02-20 17:37
 * @Description: java类描述
 */
@RestController
public class HystrixController {
    @Autowired
    private HystrixService service;

    /**
     * 调用依赖的服务
     */
    @RequestMapping("/call")
    public String callDependencyService(){
        return service.callDependencyService();
    }
}
