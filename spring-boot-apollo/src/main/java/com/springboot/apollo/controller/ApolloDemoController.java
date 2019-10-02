package com.springboot.apollo.controller;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.springboot.apollo.util.PropertiesUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;
import java.util.Set;

/**
 * @Author: Dong.L
 * @Create: 2019-09-10 17:16
 * @Description: java类描述
 */
@RestController
@RequestMapping("/apollo")
public class ApolloDemoController {
    /**
     * 从apollo获取配置信息
     */
    @ApolloConfig
    private Config config;

    @GetMapping("/read_demo")
    public Properties apolloReadDemo() {
        System.out.println("->> apolloReadDemo");
        // 得到当前app.id中的配置
        Set<String> set = config.getPropertyNames();
        for (String key : set) {
            PropertiesUtils.properties.setProperty(key, config.getProperty(key, null));
        }
        for (String key : PropertiesUtils.properties.stringPropertyNames()) {
            System.out.println(key + ">>>" + PropertiesUtils.properties.getProperty(key));
        }
        return PropertiesUtils.properties;
    }
}
