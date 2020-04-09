/*
 * **************************************************************
 * Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * 项目名称：springboot-docker
 * 文件名称：FanoutSender.java
 * 代码说明：TODO
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2020/4/9 17:05 v1.0.0 初始创建
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
 * @CreateDate: Created in 2020/4/9 17:05
 * @Author: Dong.L
 **/
@RestController
public class FanoutSender {
    @Autowired
    AmqpTemplate rabbitTemplate;

    /**
     * @param
     * @method: fanSender
     * @description: 分别绑定了fanoutExchange交换器，
     *  中间的是交换器选择队列是的条件routerKey，这个在后面的Topic模式中会用到，
     *  现在因为是所有队列都会收到，所有就没有条件
     * @return: String
     * @throws:
     * @author: Dong.L
     * @date: 2020/4/9 17:09
     */
    @GetMapping("/fanout")
    public String fanSender() {
        String message1 = "FanSender1111:" + System.currentTimeMillis();
        System.out.println("--> message1: " + message1);
        this.rabbitTemplate.convertAndSend("fanoutExchange", "", message1);

        String message2 = "FanSender2222:" + System.currentTimeMillis();
        System.out.println("--> message2: " + message2);
        this.rabbitTemplate.convertAndSend("fanoutExchange", "", message2);
        return message1 + "\n" + message2;
    }

}
