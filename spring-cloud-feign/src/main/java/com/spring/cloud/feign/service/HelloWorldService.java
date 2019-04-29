package com.spring.cloud.feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: Dong.L
 * @Create: 2019-02-20 16:01
 * @Description: java类描述
 */
@FeignClient(value = "CONFIG-CLIENT")
public interface HelloWorldService {

    @RequestMapping(value = "/add/{a}/{b}",method = RequestMethod.GET)
    String sayHello(@RequestParam("a") Integer a, @RequestParam("b") Integer b);
}
