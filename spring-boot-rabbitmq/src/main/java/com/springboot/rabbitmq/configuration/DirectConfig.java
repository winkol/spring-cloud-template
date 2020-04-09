/*
 * **************************************************************
 * Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * 项目名称：springboot-docker
 * 文件名称：OneByOneConfig.java
 * 代码说明：TODO
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2020/4/9 16:50 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.springboot.rabbitmq.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: Direct就是一对一模式
 *  (Direct就是一个发送者对应一个接收者。如果有多个，只会有一个接收到消息。)
 * @Project: com.ld.rabbitmq.config
 * @CreateDate: Created in 2020/4/9 16:50
 * @Author: Dong.L
 **/
@Configuration
public class DirectConfig {
    @Bean
    public Queue oneQueue(){
        return new Queue("OneByOne");
    }
}
