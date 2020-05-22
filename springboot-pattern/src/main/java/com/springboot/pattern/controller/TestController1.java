/*
 * **************************************************************
 * Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * 项目名称：spring-cloud-template
 * 文件名称：TestController1.java
 * 代码说明：TODO
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2020/5/20 16:49 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.springboot.pattern.controller;

import com.google.common.collect.ImmutableMap;
import com.springboot.pattern.handler.AbstractReqTypeHandler;
import com.springboot.pattern.handler.CompositeCpaCallBack;
import com.springboot.pattern.util.ControllerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Description: TODO
 * @Project: com.springboot.pattern.controller
 * @CreateDate: Created in 2020/5/20 16:49
 * @Author: Dong.L
 **/
@Slf4j
@RestController
public class TestController1 {

    @Autowired
    private ControllerContext context;

    @Autowired
    private CompositeCpaCallBack compositeCpaCallBack;

    @GetMapping("/test/{reqType}")
    public Map<String, Object> test1(@PathVariable("reqType") String reqType) {
        log.info("->> controller reqType: {}", reqType);
        Map<String, Object> resultMap = null;
        try {
            AbstractReqTypeHandler abstractReqTypeHandler = context.getInstance(reqType);
            resultMap = abstractReqTypeHandler.handler(reqType);
        } catch (Exception ex) {
            log.error("error", ex);
            return ImmutableMap.of("message", "FAIL");
        }
        return resultMap;
    }

    @GetMapping("/test2")
    public String test2(){
        Boolean callResult = compositeCpaCallBack.callbackAdvertisers("jinritoutiao");
        log.info("->> callResult: {}", callResult);
        return "ok";
    }
}
