
package com.springboot.druid.configuration;

import lombok.extern.slf4j.Slf4j;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @Author:  Dong.L
 * @Date:    2019/11/20 14:40
 * @Description: RestTemplate配置
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
