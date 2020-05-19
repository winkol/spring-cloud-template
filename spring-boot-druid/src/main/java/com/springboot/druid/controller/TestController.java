/*
 * **************************************************************
 * Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * 项目名称：spring-cloud-template
 * 文件名称：TestController.java
 * 代码说明：TODO
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2020/5/19 11:17 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.springboot.druid.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Description: TODO
 * @Project: com.springboot.druid.controller
 * @CreateDate: Created in 2020/5/19 11:17
 * @Author: Dong.L
 **/
@RestController
public class TestController {

    @Autowired
    @Qualifier("cepRestTemplate")
    private RestTemplate restTemplate;

    @GetMapping("/test")
    public String test() {
        String url = "http://localhost:7013/testMap/1";
        restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(new HttpHeaders()), String.class);
        return "ok";
    }

}
