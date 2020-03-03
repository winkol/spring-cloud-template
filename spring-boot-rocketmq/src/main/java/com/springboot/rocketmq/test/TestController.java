/*
 * **************************************************************
 * Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * 项目名称：spring-cloud-template
 * 文件名称：TestController.java
 * 代码说明：TODO
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2020/3/2 18:15 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.springboot.rocketmq.test;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Description: TODO
 * @Project: com.springboot.rocketmq.test
 * @CreateDate: Created in 2020/3/2 18:15
 * @Author: Dong.L
 **/
@Slf4j
@RestController
public class TestController {
    /**
     * 使用RocketMq的生产者
     */
    @Autowired
    private DefaultMQProducer defaultMQProducer;

    private List<String> mesList;

    public TestController() {
        mesList = new ArrayList<>();
        mesList.add("张三");
        mesList.add("李四");
        mesList.add("王五");
        mesList.add("赵六");
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public Object test() throws Exception {
        /*String msg = "demo msg test";
        log.info("开始发送消息：" + msg);
        Message sendMsg = new Message("DemoTopic", "DemoTag", msg.getBytes());
        //默认3秒超时
        SendResult sendResult = defaultMQProducer.send(sendMsg);
        log.info("消息发送响应信息：" + sendResult.toString());*/

        int orderId = 0;
        for (String msg : mesList) {
            orderId++;
            log.info("开始发送消息：" + msg);
            Message sendMsg = new Message("DemoTopic",
                    "DemoTag",
                    "key-" + orderId,
                    ("测试" + orderId + msg).getBytes());
            // 无序发送
//            SendResult sendResult = defaultMQProducer.send(sendMsg);

            // 有序发送
            SendResult sendResult = defaultMQProducer.send(sendMsg, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> mqs, Message message, Object arg) {
                    // 这里的arg就是orderId传进来的
                    Integer id = (Integer) arg;
                    // 取模决定放在哪个数据库
                    int index = id % mqs.size();
                    return mqs.get(index);
                }
            }, 127001);

            log.info("消息发送响应信息：" + sendResult.toString());
        }
        return "成功";
    }

    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    public Object test2() throws InterruptedException {
        for (int i = 1; i <= 10; i++) {
            try {

                /**
                 * TopicTest要发送的队列
                 * TagA标签,可以达到再次过滤,消费端可以只消费TagA的消息类似于这样
                 * key可以在控制台根据key查询消息
                 * body消息体内容
                 */
                Message msg = new Message("DemoTopic",// topic
                        "TagA",// tag
                        "key1" + i,// key
                        ("订单一号" + i).getBytes());// body
                SendResult sendResult = defaultMQProducer.send(msg, new MessageQueueSelector() {
                    @Override
                    public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                        //这里的arg就是orderId传进来的
                        Integer id = (Integer) arg;
                        //取模决定放在哪个数据库
                        int index = id % mqs.size();
                        return mqs.get(index);
                    }
                }, 1);//订单号为1
                log.info("{}",sendResult);


            } catch (Exception e) {
                e.printStackTrace();
            }
            TimeUnit.MILLISECONDS.sleep(1000);
        }
        for (int i = 1; i <= 10; i++) {
            try {
                int orderId = i;
                Message msg = new Message("DemoTopic",// topic
                        "TagA",// tag
                        "key2" + i,// key
                        ("订单二号" + i).getBytes());// body
                SendResult sendResult = defaultMQProducer.send(msg, new MessageQueueSelector() {
                    @Override
                    public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                        //这里的arg就是orderId传进来的
                        Integer id = (Integer) arg;
                        //取模决定放在哪个数据库
                        int index = id % mqs.size();
                        return mqs.get(index);
                    }
                }, 2);//订单号为2
                log.info("{}",sendResult);


            } catch (Exception e) {
                e.printStackTrace();
            }
            TimeUnit.MILLISECONDS.sleep(1000);
        }
        for (int i = 1; i <= 10; i++) {
            try {
                int orderId = i;
                Message msg = new Message("DemoTopic",// topic
                        "TagA",// tag
                        "key3" + i,// key
                        ("订单三号" + i).getBytes());// body
                SendResult sendResult = defaultMQProducer.send(msg, new MessageQueueSelector() {
                    @Override
                    public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                        //这里的arg就是orderId传进来的
                        Integer id = (Integer) arg;
                        //取模决定放在哪个数据库
                        int index = id % mqs.size();
                        return mqs.get(index);
                    }
                }, 3);//订单号为3
                log.info("{}",sendResult);

            } catch (Exception e) {
                e.printStackTrace();
            }
            TimeUnit.MILLISECONDS.sleep(1000);
        }
        return "success";
    }

}
