package com.springboot.apollo.configuration;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @Author: Dong.L
 * @Create: 2019-09-11 11:19
 * @Description: 加载类
 */
@Configuration
/**
 * 在微服务应用启动中使用apollo配置中心获取配置信息
 */
@EnableApolloConfig
@Import({JavaConfigBean.class, QuartzConfig.class, PropertiesRefresher.class})
public class ApolloConfig {
}
