/*
 * **************************************************************
 * Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * 项目名称：spring-cloud-template
 * 文件名称：MockController.java
 * 代码说明：TODO
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2020/4/21 14:35 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.springboot.junit5.controller;

import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: TODO
 * @Project: com.springboot.junit5.controller
 * @CreateDate: Created in 2020/4/21 14:35
 * @Author: Dong.L
 **/
@Slf4j
@RestController
public class MockController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/hallo")
    public Map<String, Object> hallo(String content){
        log.info("->> mock hallo");
        log.info("->> content: {}", content);
        Map<String, Object> respMap = restTemplate.postForObject("", new HashMap<>(), Map.class);
        if (null == respMap) {
            respMap = ImmutableMap.of("msg", "success");
        }
        return respMap;
    }

     public void notResp(){
        log.info("->> mock not response");
        restTemplate.setErrorHandler(null);
     }

}