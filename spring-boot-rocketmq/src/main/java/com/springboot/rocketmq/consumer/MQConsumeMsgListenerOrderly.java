/*
 * **************************************************************
 * Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * 项目名称：spring-cloud-template
 * 文件名称：MQConsumeMsgListenerProcessor.java
 * 代码说明：TODO
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2020/3/2 18:03 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.springboot.rocketmq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

/**
 * @Description: TODO
 * @Project: com.springboot.rocketmq.consumer
 * @CreateDate: Created in 2020/3/2 18:03
 * @Author: Dong.L
 **/
@Slf4j
//@Component
public class MQConsumeMsgListenerOrderly implements MessageListenerOrderly {

    @Value("${rocketmq.consumer.topics}")
    private String topics;

    @Override
    public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext consumeOrderlyContext) {
        for (int i = 0; i < msgs.size(); i++) {
            MessageExt msg = msgs.get(i);
            log.info("{}, {}, {}", msg.getTopic(), msg.getTags(), new String(msg.getBody()));
        }
        return ConsumeOrderlyStatus.SUCCESS;
    }
}
