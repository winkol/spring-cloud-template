/*
 * **************************************************************
 * Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * 项目名称：springboot-docker
 * 文件名称：Fanout1Reciver.java
 * 代码说明：TODO
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2020/4/9 17:11 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.springboot.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description: fanout 消费
 * @Project: com.ld.rabbitmq.consumer
 * @CreateDate: Created in 2020/4/9 17:11
 * @Author: Dong.L
 **/
@Component
@RabbitListener(queues = "fanout.c")
public class Fanout3Receiver {
    @RabbitHandler
    public void receiver(String message){
        System.out.println("FanoutReceiver---fanout.c:"+message);
    }
}
