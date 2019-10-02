package com.springboot.apollo.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: Dong.L
 * @Create: 2019-09-11 11:18
 * @Description: java类描述
 */
@ConfigurationProperties(prefix = "quartz")
public class QuartzConfig {
    private String scheduler1;
    private String scheduler2;

    public String getScheduler1() {
        return scheduler1;
    }

    public void setScheduler1(String scheduler1) {
        this.scheduler1 = scheduler1;
    }

    public String getScheduler2() {
        return scheduler2;
    }

    public void setScheduler2(String scheduler2) {
        this.scheduler2 = scheduler2;
    }
}
