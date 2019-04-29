package com.spring.cloud.feign.controller;

import com.spring.cloud.feign.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Dong.L
 * @Create: 2019-02-20 16:05
 * @Description: java类描述
 */
@RestController
public class WebController {
    @Autowired
    private HelloWorldService helloWorldService;

    @RequestMapping(value = "/feignHi/{a}/{b}",method = RequestMethod.GET)
    public String sayHello(@PathVariable Integer a, @PathVariable Integer b){
        System.out.println("->> feignHi.start");
        return helloWorldService.sayHello(a, b);
    }
}
