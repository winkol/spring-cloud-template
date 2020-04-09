/*
 * **************************************************************
 * Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * 项目名称：springboot-docker
 * 文件名称：OneByOneReceiver.java
 * 代码说明：TODO
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2020/4/9 16:56 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.springboot.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description: Direct 消费
 *  --@RabbitListener是用来绑定队列的，该接收者绑定了OneByOne这个队列
 *  --@RabbitHandler注解是用来表示该方法是接收者接收消息的方法
 * @Project: com.ld.rabbitmq.consumer
 * @CreateDate: Created in 2020/4/9 16:56
 * @Author: Dong.L
 **/
@Component
@RabbitListener(queues = "OneByOne")
public class DirectReceiver {
    @RabbitHandler
    public void receiver(String context) {
        System.out.println("OneByOne-Receiver::::" + context);
    }
}
