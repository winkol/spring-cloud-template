/*
 * **************************************************************
 * Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * 项目名称：spring-cloud-template
 * 文件名称：MQProducerConfiguration.java
 * 代码说明：生产者
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2020/3/2 17:21 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.springboot.rocketmq.producer;

import com.springboot.rocketmq.constants.RocketMQErrorEnum;
import com.springboot.rocketmq.exception.RocketMQException;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

/**
 * @Description: 生产者
 * @Project: com.springboot.rocketmq.producer
 * @CreateDate: Created in 2020/3/2 17:21
 * @Author: Dong.L
 **/
@Slf4j
@Configuration
public class MQProducerConfiguration {
    /**
     * 发送同一类消息的设置为同一个group，保证唯一,默认不需要设置，rocketmq会使用ip@pid(pid代表jvm名字)作为唯一标示
     */
    @Value("${rocketmq.producer.groupName}")
    private String groupName;

    @Value("${rocketmq.producer.namesrvAddr}")
    private String namesrvAddr;

    /**
     * 消息最大大小，默认4M
     */
    @Value("${rocketmq.producer.maxMessageSize}")
    private Integer maxMessageSize;
    /**
     * 消息发送超时时间，默认3秒
     */
    @Value("${rocketmq.producer.sendMsgTimeout}")
    private Integer sendMsgTimeout;
    /**
     * 消息发送失败重试次数，默认2次
     */
    @Value("${rocketmq.producer.retryTimesWhenSendFailed}")
    private Integer retryTimesWhenSendFailed;

    @Bean
    public DefaultMQProducer getRocketMQProducer() throws RocketMQException {

        if (StringUtils.isEmpty(this.groupName)) {
            throw new RocketMQException(RocketMQErrorEnum.PARAMM_NULL, "groupName is blank", false);
        }

        if (StringUtils.isEmpty(this.namesrvAddr)) {
            throw new RocketMQException(RocketMQErrorEnum.PARAMM_NULL, "nameServerAddr is blank", false);
        }

        DefaultMQProducer producer;
        producer = new DefaultMQProducer(this.groupName);

        producer.setNamesrvAddr(this.namesrvAddr);
        producer.setCreateTopicKey("AUTO_CREATE_TOPIC_KEY");

        //如果需要同一个jvm中不同的producer往不同的mq集群发送消息，需要设置不同的instanceName
        //producer.setInstanceName(instanceName);
        if (this.maxMessageSize != null) {
            producer.setMaxMessageSize(this.maxMessageSize);
        }
        if (this.sendMsgTimeout != null) {
            producer.setSendMsgTimeout(this.sendMsgTimeout);
        }
        //如果发送消息失败，设置重试次数，默认为2次
        if (this.retryTimesWhenSendFailed != null) {
            producer.setRetryTimesWhenSendFailed(this.retryTimesWhenSendFailed);
        }

        try {
            producer.start();
            log.info(String.format("producer is start ! groupName:[%s],namesrvAddr:[%s]", this.groupName, this.namesrvAddr));
        } catch (MQClientException e) {
            log.error("producer is error {}, \n{}", e.getMessage(), e);
            throw new RocketMQException(e);
        }
        return producer;

    }
}
