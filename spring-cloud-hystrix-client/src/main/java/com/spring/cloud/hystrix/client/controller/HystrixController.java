package com.spring.cloud.hystrix.client.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @Author: Dong.L
 * @Create: 2019-02-20 17:37
 * @Description: java类描述
 */
@RestController
public class HystrixController {

    /**
     * 调用依赖的服务
     * HystrixCommand- 如果当前调用的get()方法出现了错误，则执行fallback
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod="getFallback")
    @RequestMapping("/call/{id}")
    public Object callDependencyService(@PathVariable("id") Integer id){
        System.out.println("call");
        // 数据不存在，假设让它抛出个错误
        if (id <= 0) {
            throw new RuntimeException("部门信息不存在！") ;
        }
        return new HashMap<String, Object>(2){{
            put("zero", id);
            put("one", "张三");
            put("two", "李四");
        }};
    }
    public String getFallback(Integer id){
        System.out.println("getFallback id: " + id);
        return "error";
    }
}
