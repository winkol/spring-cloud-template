package com.spring.cloud.hystrix.client;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

/**
* @Author:  Man.Luo
* @Date:    2019/2/21 10:08
* @Description: 监控测试
*/
@EnableEurekaClient
@EnableCircuitBreaker
@SpringBootApplication
public class SpringCloudHystrixClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudHystrixClientApplication.class, args);
    }

    /**
     * Spring Boot 2.0 下 hystrix.stream 404 问题，需要添加如下配置
     * @return
     */
    @Bean
    public ServletRegistrationBean getServlet() {
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/actuator/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }

}
