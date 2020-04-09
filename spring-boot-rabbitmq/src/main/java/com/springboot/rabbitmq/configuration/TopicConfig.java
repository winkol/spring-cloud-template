/*
 * **************************************************************
 * Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * 项目名称：springboot-docker
 * 文件名称：TopicConfig.java
 * 代码说明：TODO
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2020/4/9 17:16 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.springboot.rabbitmq.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: Topic模式就相当于发布订阅模式交换机和队列之间加上了一定的匹配规则。
 *  只有符合规则的消息才能到这个队列中去从而被接收者收到
 * @Project: com.ld.rabbitmq.config
 * @CreateDate: Created in 2020/4/9 17:16
 * @Author: Dong.L
 **/
@Configuration
public class TopicConfig {
    /**
     * 可以看到创建了三个队列和一个交换器，并且将交换器和队列进行了绑定，
     * 在绑定的过程中多了一个条件with,这是一种通配符方式的匹配，
     * . 为分隔符，*代表一个，#表示0个或者多个，
     * 如上面的topic.#就可已匹配，topic，topic.z，topic.ma.z.z.z等，
     * 而topic.*.z就可以匹配topic.m.z,topic.z.z等，而topic.msg就只能匹配topic.msg条件的消息
     * @return
     */

    @Bean
    public Queue topicQueue1(){
        return new Queue("topic.a");
    }
    @Bean
    public Queue topicQueue2(){
        return new Queue("topic.b");
    }
    @Bean
    public Queue topicQueue3(){
        return new Queue("topic.c");
    }

    @Bean
    TopicExchange topicExchange(){
        return new TopicExchange("topicExchange");
    }

    @Bean
    public Binding binding1(){
        return BindingBuilder.bind(topicQueue1()).to(topicExchange()).with("topic.msg");
    }

    @Bean
    public Binding binding2(){
        return BindingBuilder.bind(topicQueue2()).to(topicExchange()).with("topic.#");
    }

    @Bean
    public Binding binding3(){
        return BindingBuilder.bind(topicQueue3()).to(topicExchange()).with("topic.*.z");
    }
}
