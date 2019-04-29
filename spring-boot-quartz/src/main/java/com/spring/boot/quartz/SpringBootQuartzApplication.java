package com.spring.boot.quartz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ImportResource;

/**
 * 简单 Quartz-Cluster 微服务，支持集群分布式，并支持动态修改 Quartz 任务的 cronExpression 执行时间。
 *
 * @author hmilyylimh
 *
 * @version 0.0.1
 *
 * @date 17/9/18
 * https://blog.csdn.net/YLIMH_HMILY/article/details/78007004
 */
@EnableEurekaClient
@SpringBootApplication
@ImportResource("quartz.xml")
public class SpringBootQuartzApplication {
    private static final Logger Logger = LoggerFactory.getLogger(SpringBootQuartzApplication.class);

    public static void main(String[] args) {
        Logger.info("简单Quartz-Cluster微服务入口函数编码-" + System.getProperty("file.encoding"));

        SpringApplication.run(SpringBootQuartzApplication.class, args);

        System.out.println("【【【【【【 简单Quartz-Cluster微服务 】】】】】】已启动.");
    }

}
