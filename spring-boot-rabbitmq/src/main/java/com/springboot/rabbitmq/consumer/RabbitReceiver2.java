/*
 * **************************************************************
 * Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * 项目名称：springboot-docker
 * 文件名称：Topic4Receiver.java
 * 代码说明：TODO
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2020/4/9 17:48 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.springboot.rabbitmq.consumer;

import com.rabbitmq.client.Channel;
import com.springboot.rabbitmq.exception.ServiceException;
import com.springboot.rabbitmq.model.Order;
import com.springboot.rabbitmq.util.ObjectMapperUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * @Description: TODO
 * @Project: com.ld.rabbitmq.consumer
 * @CreateDate: Created in 2020/4/9 17:48
 * @Author: Dong.L
 **/
@Slf4j
@Component
public class RabbitReceiver2 {

    @RabbitHandler
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${spring.rabbitmq.listener.order.queue.name}",
                    durable = "${spring.rabbitmq.listener.order.queue.durable}"),
            exchange = @Exchange(value = "${spring.rabbitmq.listener.order.exchange.name}",
                    durable = "${spring.rabbitmq.listener.order.exchange.durable}",
                    type = "${spring.rabbitmq.listener.order.exchange.type}",
                    ignoreDeclarationExceptions = "${spring.rabbitmq.listener.order.exchange.ignoreDeclarationExceptions}"),
            key = "${spring.rabbitmq.listener.order.key}"
    )
    )
    public void handleReceive(Channel channel, @Payload Order object, Message message, @Headers Map<String, Object> headers) {
        log.info("接收到的json：" + ObjectMapperUtils.writeValueAsString(object));
        if (headers.get("error") != null) {
            log.info("错误的消息");
            try {
                //否认消息,拒接该消息重回队列
                channel.basicNack((Long) headers.get(AmqpHeaders.DELIVERY_TAG), false, false);
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            int i = 10 / 0;
            log.info("->> 消费成功");
        } catch (Throwable t) {
            log.info("->> 消费失败");
            throw t;
        }
    }

    @RabbitHandler
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${spring.rabbitmq.listener.order.queue.name}",
                    durable = "${spring.rabbitmq.listener.order.queue.durable}"),
            exchange = @Exchange(value = "${spring.rabbitmq.listener.order.exchange.name}",
                    durable = "${spring.rabbitmq.listener.order.exchange.durable}",
                    type = "${spring.rabbitmq.listener.order.exchange.type}",
                    ignoreDeclarationExceptions = "${spring.rabbitmq.listener.order.exchange.ignoreDeclarationExceptions}"),
            key = "rabbitmq.*"
    )
    )
    public void handleReceive2(Channel channel, @Payload Order object, Message message, @Headers Map<String, Object> headers) {
        log.info("接收到的json：" + ObjectMapperUtils.writeValueAsString(object));
        if (headers.get("error") != null) {
            log.info("错误的消息");
            try {
                //否认消息,拒接该消息重回队列
                channel.basicNack((Long) headers.get(AmqpHeaders.DELIVERY_TAG), false, false);
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            log.info("->> 消费成功");
            if (1==1) {
                throw new ServiceException("手动，消费失败");
            }
        } catch (Throwable t) {
            log.info("->> 消费失败");
            throw t;
        }
    }

//    @RabbitHandler
//    @RabbitListener(queues = "queue-2")
    public void receiveObjectDel(Channel channel, @Payload Order object, Message message, @Headers Map<String, Object> headers) {

        log.info("接收到的json：{}", ObjectMapperUtils.writeValueAsString(object));

        //<P>代码为在消费者中开启消息接收确认的手动ack</p>
        //<H>配置完成</H>
        //<P>可以开启全局配置</p>
        if (headers.get("error") != null) {
            log.info("错误的消息");
            try {
                //否认消息,拒接该消息重回队列
                channel.basicNack((Long) headers.get(AmqpHeaders.DELIVERY_TAG), false, false);
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //手动ACK
        //默认情况下如果一个消息被消费者所正确接收则会被从队列中移除
        //如果一个队列没被任何消费者订阅，那么这个队列中的消息会被 Cache（缓存），
        //当有消费者订阅时则会立即发送，当消息被消费者正确接收时，就会被从队列中移除
        try {
            int i = 10 / 0;
            // 手动ack应答
            // 告诉服务器收到这条消息 已经被我消费了 可以在队列删掉 这样以后就不会再发了
            // 否则消息服务器以为这条消息没处理掉 后续还会在发，true确认所有消费者获得的消息
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            log.info("消息消费成功：id：{}", message.getMessageProperties().getDeliveryTag());
        } catch (IOException e) {
            e.printStackTrace();
            //丢弃这条消息
            try {
                //最后一个参数是：是否重回队列
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
                //拒绝消息
                //channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
                //消息被丢失
                //channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
                //消息 发送
                //channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
                //多条消息被重新发送
                //channel.basicNack(message.getMessageProperties().getDeliveryTag(), true, true);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            log.info("消息消费失败：id：{}", message.getMessageProperties().getDeliveryTag());
        }
    }

}
