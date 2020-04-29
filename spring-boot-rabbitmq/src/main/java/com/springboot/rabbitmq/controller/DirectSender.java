/*
 * **************************************************************
 * Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * 项目名称：springboot-docker
 * 文件名称：OneByOneSender.java
 * 代码说明：TODO
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2020/4/9 16:52 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.springboot.rabbitmq.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: TODO
 * @Project: com.ld.rabbitmq.controller
 * @CreateDate: Created in 2020/4/9 16:52
 * @Author: Dong.L
 **/
@RestController
public class  DirectSender {
    @Autowired
    AmqpTemplate rabbitTemplate;

    @GetMapping("/direct")
    public String send() {
        String context = "OneByOneSender" + System.currentTimeMillis();
        System.out.println("OneSender : " + context);
        this.rabbitTemplate.convertAndSend("OneByOne", context);
        return context;
    }
}
