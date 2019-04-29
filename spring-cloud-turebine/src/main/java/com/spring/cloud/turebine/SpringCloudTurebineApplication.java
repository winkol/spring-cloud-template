package com.spring.cloud.turebine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
* @Author:  Man.Luo
* @Date:    2019/2/27 9:18
* @Description: 聚合监控
*/
@EnableTurbine
@EnableEurekaClient
@SpringBootApplication
public class SpringCloudTurebineApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudTurebineApplication.class, args);
    }

}
