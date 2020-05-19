/*
 * **************************************************************
 * Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * 项目名称：spring-cloud-template
 * 文件名称：RetryTemplateTest1.java
 * 代码说明：TODO
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2020/5/13 14:52 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.dongl.utils;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;
import org.springframework.retry.RecoveryCallback;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import java.util.Collections;
import java.util.Map;

/**
 * @Description: 业务失败重试（生效需要添加@EnableRetry启动加载）
 * https://www.cnblogs.com/whatarewords/p/10656514.html
 * @Project: com.dongl.utils
 * @CreateDate: Created in 2020/5/13 14:52
 * @Author: Dong.L
 **/
public class RetryTemplateTest1 {
    @Test
    public void test1() {
        System.out.println(upload());
    }

    /**
     * 示例一
     *
     * @throws Exception
     */
    public String upload() {
        int maxAttempts = 3;
        Map<String, Object> map = ImmutableMap.of("id", "123");
        // 构建重试模板实例
        RetryTemplate retryTemplate = new RetryTemplate();
        // 设置重试策略，主要设置重试次数
        SimpleRetryPolicy policy =
                new SimpleRetryPolicy(maxAttempts, Collections.<Class<? extends Throwable>, Boolean>singletonMap(Exception.class, true));
        // 设置重试回退操作策略，主要设置重试间隔时间
        FixedBackOffPolicy fixedBackOffPolicy = new FixedBackOffPolicy();
        fixedBackOffPolicy.setBackOffPeriod(100);
        retryTemplate.setRetryPolicy(policy);
        retryTemplate.setBackOffPolicy(fixedBackOffPolicy);
        // 通过RetryCallback 重试回调实例包装正常逻辑逻辑，第一次执行和重试执行执行的都是这段逻辑
        final RetryCallback<Object, Exception> retryCallback = new RetryCallback<Object, Exception>() {
            //RetryContext 重试操作上下文约定，统一spring-try包装
            public Object doWithRetry(RetryContext context) throws Exception {
                System.out.println("do some thing");
                Exception e = uploadToOdps(map);
                System.out.println("some: " + context.getRetryCount());
                if (null == e) {
                    return "ok";
                }
                throw e;//这个点特别注意，重试的根源通过Exception返回
            }
        };
        // 通过RecoveryCallback 重试流程正常结束或者达到重试上限后的退出恢复操作实例
        final RecoveryCallback<Object> recoveryCallback = new RecoveryCallback<Object>() {
            public Object recover(RetryContext context) throws Exception {
                System.out.println("do recory operation");
                System.out.println("recory: " + context.getRetryCount());
                return "recory";
            }
        };
        try {
            // 由retryTemplate 执行execute方法开始逻辑执行
            retryTemplate.execute(retryCallback, recoveryCallback);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "completed";
    }

    private Exception uploadToOdps(Map<String, Object> map) {
        System.out.println("uploadToOdps.map: " + map);
        return new RuntimeException();
    }

}
