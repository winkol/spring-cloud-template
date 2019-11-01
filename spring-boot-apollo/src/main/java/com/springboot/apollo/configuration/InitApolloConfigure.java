package com.springboot.apollo.configuration;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.springboot.apollo.util.ApolloConfigurationChange;
import com.springboot.apollo.util.PropertiesUtilsT;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @Author: Dong.L
 * @Create: 2019-10-31 10:42
 * @Description: 读取配置
 * order是用来指定初始顺序，value越小，越早加载
 */
@Component
@Order(value = -9)
public class InitApolloConfigure implements CommandLineRunner {
    /**
     * 从apollo获取配置信息
     */
    @ApolloConfig
    private Config config;

    /**
     * commandLineRunner 目的就是在启动之后可以加载所需要的配置文件
     *
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        System.out.println("初始化>>>CommandLineRunner");
        //加载公共配置
        loadCommonConfig();
        //加载指定model的配置
        Set<String> configs = config.getPropertyNames();
        if (configs != null && !configs.isEmpty()) {
            configs.forEach(key -> {
                PropertiesUtilsT.properties.setProperty(key, config.getProperty(key, null));
            });
            //监听app.id中的key发生变化后就改变其值
            ApolloConfigurationChange.monitorApolloConfigurationChange(PropertiesUtilsT.properties, config);
        }
    }

    /**
     * 加载公共配置文件
     */
    public void loadCommonConfig() {
        Config commonConfig = ConfigService.getConfig(PropertiesUtilsT.COMMON);
        if (commonConfig != null) {
            for (String key : commonConfig.getPropertyNames()) {
                PropertiesUtilsT.properties.setProperty(key, commonConfig.getProperty(key, null));
            }
            //监听app.id中的key发生变化后就改变其值
            ApolloConfigurationChange.monitorApolloConfigurationChange(PropertiesUtilsT.properties, config);
        }
    }
}
