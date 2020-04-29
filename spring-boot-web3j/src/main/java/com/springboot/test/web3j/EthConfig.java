/*
 * **************************************************************
 * Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * 项目名称：spring-cloud-template
 * 文件名称：EthConfig.java
 * 代码说明：TODO
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2020/3/17 17:32 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.springboot.test.web3j;

import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import java.util.concurrent.TimeUnit;

/**
 * @Description: TODO
 * @Project: com.springboot.test.web3j
 * @CreateDate: Created in 2020/3/17 17:32
 * @Author: Dong.L
 **/
@Configuration
public class EthConfig {
    @Value("${web3j.client-address}")
    private String rpc;

    @Bean
    public Web3j web3j() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(30*1000, TimeUnit.MILLISECONDS);
        builder.writeTimeout(30*1000, TimeUnit.MILLISECONDS);
        builder.readTimeout(30*1000, TimeUnit.MILLISECONDS);
        OkHttpClient httpClient = builder.build();
        Web3j web3j = Web3j.build(new HttpService(rpc,httpClient,false));
        return web3j;
    }
}
