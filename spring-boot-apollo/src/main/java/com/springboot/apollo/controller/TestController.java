package com.springboot.apollo.controller;

import com.springboot.apollo.configuration.JavaConfigBean;
import com.springboot.apollo.configuration.QuartzConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Dong.L
 * @Create: 2019-09-11 10:52
 * @Description: java类描述
 */
@RestController
public class TestController {
    /**
     * Java Config方式
     */
    @Autowired
    JavaConfigBean javaConfigBean;
    /**
     * ConfigurationProperties使用方式
     */
    @Autowired
    QuartzConfig quartzConfig;

    @RequestMapping("/index")
    public String hello() {
        StringBuffer sb = new StringBuffer();
        sb.append(javaConfigBean.getUserName()).append(" --------- ")
                .append(quartzConfig.getScheduler1()).append(" --------- ")
                .append(quartzConfig.getScheduler2()).append(" --------- ")
                .append(javaConfigBean.getTimeout()).append(" --------- ")
        ;
        System.out.println("->>>>>>>>>>>> " + sb.toString());
        return sb.toString();
    }
}
