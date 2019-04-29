package com.cloud.config.client2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
* @Author:  Man.Luo
* @Date:    2019/2/18 15:48
* @Description: 测试
*/
@EnableEurekaClient
@SpringBootApplication
public class CloudConfigClient2Application {

    public static void main(String[] args) {
        SpringApplication.run(CloudConfigClient2Application.class, args);
    }

}
