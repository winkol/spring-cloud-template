/*
 * **************************************************************
 * Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * 项目名称：springboot-docker
 * 文件名称：TopicSender.java
 * 代码说明：TODO
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2020/4/9 17:18 v1.0.0 初始创建
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
 * @CreateDate: Created in 2020/4/9 17:18
 * @Author: Dong.L
 **/
@RestController
public class TopicSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    @GetMapping("/topic")
    public String topicSend() {
        topicSend1();
        topicSend2();
        topicSend3();
        return "topic ok.";
    }

    /**
     * 其中
     * this.rabbitTemplate.convertAndSend("topicExchange", "topic.good.msg", dateString);
     * 发送者发送消息时，传的三个参数，第一个时你要传给的交换机，
     * 第二个是传给交换机的条件，在Topic模式中，队列与交换机会有一个匹配的条件，
     * 如果现在有三个队列和交换机绑定，分别条件是：A: topic.# ,B: topic.msg, C:topic.*.z（#代表多个，*代表一个）。
     *
     * 则上面代码给的key时 topic.good.msg 就只能匹配到A队列中去。
     * 如果时topic.msg，那么就匹配到B队列中了，如果是topic.good.z/topic.msg.z 那么会匹配到A和C两个队列中去。
     *
     * 而同时，只要绑定了A,B,C的队列的接收者，
     * 如果上面匹配成功，消息就会被发布到队列中，相应的绑定了该队列的接收者就会获取到该消息。
     */

    public void topicSend1() {
        String dateString = "[topic.msg] Send1 msg:" + System.currentTimeMillis();
        System.out.println(dateString);
        this.rabbitTemplate.convertAndSend("topicExchange", "topic.msg", dateString);
    }

    public void topicSend2() {
        String dateString = "[topic.good.msg] Send2 msg:" + System.currentTimeMillis();
        System.out.println(dateString);
        this.rabbitTemplate.convertAndSend("topicExchange", "topic.good.msg", dateString);
    }

    public void topicSend3() {
        String dateString = "[topic.m.z] Send3 msg:" + System.currentTimeMillis();
        System.out.println(dateString);
        this.rabbitTemplate.convertAndSend("topicExchange", "topic.m.z", dateString);
    }
}
