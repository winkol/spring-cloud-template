package com.springboot.rabbitmq.consumer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author: Dong.L
 * @Create: 2019-04-28 16:20
 * @Description: 发送者
 */
@Component
public class HelloSender {
    /**
     * rabbitTemplate 是 Spring Boot 提供的默认实现
     */
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String context = "hello " + new Date();
        System.err.println("->> Sender: " + context);
        this.rabbitTemplate.convertAndSend("hello", context);
    }
}
