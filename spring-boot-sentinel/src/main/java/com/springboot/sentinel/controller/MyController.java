/*
 * **************************************************************
 * Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * 项目名称：spring-cloud-template
 * 文件名称：MyController.java
 * 代码说明：TODO
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2019/12/20 10:32 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.springboot.sentinel.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: TODO
 * @Project: com.springboot.sentinel.controller
 * @CreateDate: Created in 2019/12/20 10:32
 * @Author: Dong.L
 **/
@RestController
public class MyController {

    @GetMapping(value = "/hello")
    @SentinelResource("hello")
    public String hello() {
        return "Hello Sentinel";
    }

    @GetMapping(value = "/mye")
    @SentinelResource("mye")
    public String mye() {
        if (true) {
            throw new RuntimeException("mye");
        }
        return "mye Sentinel";
    }

    @GetMapping(value = "/myrate")
    @SentinelResource("myrate")
    public String myrate() {
        return "myrate Sentinel";
    }

}
