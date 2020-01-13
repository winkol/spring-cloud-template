/*
* **************************************************************
* Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
* RIGHTS RESERVED.
* **************************************************************
* PROJECT INFORMATION:
* 项目名称：RestTemplateConfig
* 文件名称：RestTemplateConfig.java
* 代码说明：RestTemplate配置
* **************************************************************
* CHANGE HISTORY:
* Author Date Version Reason
* Dong.L 2020/1/13 15:24 v1.0.0 初始创建
*
* **************************************************************
*/
package com.dongl.utils.configuration;

import lombok.extern.slf4j.Slf4j;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @Description: RestTemplate配置
 * @Project: RestTemplateConfig
 * @CreateDate: Created in 2020/1/13 15:24
 * @Author: Dong.L
 */
@Slf4j
@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(OkHttpClient okHttpClient) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(new OkHttp3ClientHttpRequestFactory(okHttpClient));
        return restTemplate;
    }

    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder()
                .connectionPool(connectionPool())
                //设置链接超时
                .connectTimeout(10_000, TimeUnit.MILLISECONDS)
                // 设置读数据超时
                .readTimeout(10_000, TimeUnit.MILLISECONDS)
                // 设置写数据超时
                .writeTimeout(10_000, TimeUnit.MILLISECONDS)
                .build();
    }

    @Bean
    public ConnectionPool connectionPool() {
        return new ConnectionPool(100, 30, TimeUnit.MINUTES);
    }

}
