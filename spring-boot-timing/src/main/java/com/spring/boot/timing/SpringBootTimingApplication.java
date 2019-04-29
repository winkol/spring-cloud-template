package com.spring.boot.timing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
* @Author:  Man.Luo
* @Date:    2019/2/28 14:29
* @Description: 定时任务
*/
@EnableEurekaClient
@SpringBootApplication
public class SpringBootTimingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootTimingApplication.class, args);
    }

}
