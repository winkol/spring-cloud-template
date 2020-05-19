package com.springboot.druid.configuration;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: Dong.L
 * @Create: 2019-11-15 15:58
 * @Description: java类描述
 */
@Configuration
@ComponentScan(basePackages = "com.springboot.druid")
@MapperScan("com.springboot.druid.mapper")
@Import({/*DataSourceConfig.class,*/})
public class DruidConfiguration {
    @Bean("cepRestTemplate")
    public RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new LoggingClientHttpRequestInterceptor());
        return restTemplate;
    }
}
