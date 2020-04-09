/*
 * **************************************************************
 * Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * 项目名称：springboot-docker
 * 文件名称：Topic1Reciver.java
 * 代码说明：TODO
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2020/4/9 17:26 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.springboot.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description: Topic 消费
 * @Project: com.ld.rabbitmq.consumer
 * @CreateDate: Created in 2020/4/9 17:26
 * @Author: Dong.L
 **/
@Component
@RabbitListener(queues = "topic.a")
public class Topic1Receiver {
    @RabbitHandler
    public void receiver(String message){
        System.out.println("topic.A--topic.msg--Receiver::::"+message);
    }
}
