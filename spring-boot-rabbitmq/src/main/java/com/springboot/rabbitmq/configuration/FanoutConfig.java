/*
 * **************************************************************
 * Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * 项目名称：springboot-docker
 * 文件名称：FanoutConfig.java
 * 代码说明：TODO
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2020/4/9 17:01 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.springboot.rabbitmq.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: Fanout模式就是发布订阅模式，发布者发布了消息时候顺便绑定交换器，
 *  交换器又是跟队列绑定的，那么跟这个交换器绑定的所有队列都会收到这个消息，
 *  相应的绑定了这些队列的所有接收者都会接收到发送的消息
 * @Project: com.ld.rabbitmq.product
 * @CreateDate: Created in 2020/4/9 17:01
 * @Author: Dong.L
 **/
@Configuration
public class FanoutConfig {
    /**
     * 可以看到上面创建了三个队列，到时候再创建三个接收者，
     * 那么这三个接收者再Fanout模式下，只要发布者绑定了该fanoutExchange交换器，
     * 那么他们就应该都可以收到消息。
     */

    /**
     * 队列1
     * @return
     */
    @Bean
    public Queue fanoutQueue1(){
        return new Queue("fanout.a");
    }

    /**
     * 队列2
     * @return
     */
    @Bean
    public Queue fanoutQueue2(){
        return new Queue("fanout.b");
    }

    /**
     * 队列3
     * @return
     */
    @Bean
    public Queue fanoutQueue3(){
        return new Queue("fanout.c");
    }

    /**
     * 交换器
     * @return
     */
    @Bean
    FanoutExchange fanoutExchange(){
        return new FanoutExchange("fanoutExchange");
    }

    /**
     * 绑定交换器和队列1
     * @return
     */
    @Bean
    Binding bindingFanout1(){
        return BindingBuilder.bind(fanoutQueue1()).to(fanoutExchange());
    }

    /**
     * 绑定交换器和队列2
     * @return
     */
    @Bean
    Binding bindingFanout2(){
        return BindingBuilder.bind(fanoutQueue2()).to(fanoutExchange());
    }

    /**
     * 绑定交换器和队列3
     * @return
     */
    @Bean
    Binding bindingFanout3(){
        return BindingBuilder.bind(fanoutQueue3()).to(fanoutExchange());
    }
}
