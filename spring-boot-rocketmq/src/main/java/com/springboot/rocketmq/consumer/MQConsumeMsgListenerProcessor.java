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
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Description: TODO
 * @Project: com.springboot.rocketmq.consumer
 * @CreateDate: Created in 2020/3/2 18:03
 * @Author: Dong.L
 **/
@Slf4j
@Component
public class MQConsumeMsgListenerProcessor implements MessageListenerConcurrently {

    @Value("${rocketmq.consumer.topics}")
    private String topics;

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        if (CollectionUtils.isEmpty(msgs)) {
            log.info("接收到的消息为空，不做任何处理");
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
        MessageExt messageExt = msgs.get(0);
        String msg = new String(messageExt.getBody());
        log.info("接收到的消息是：" + messageExt.toString());
        log.info("：{}", msg);
        if (messageExt.getTopic().equals(topics)) {
            if (messageExt.getTags().equals("DemoTag")) {
                int reconsumeTimes = messageExt.getReconsumeTimes();
                if (reconsumeTimes == 3) {
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
                //TODO 处理对应的业务逻辑
            }
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
