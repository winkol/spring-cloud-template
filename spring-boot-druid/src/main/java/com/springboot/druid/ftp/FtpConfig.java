package com.springboot.druid.ftp;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Author: Dong.L
 * @Create: 2019-11-21 10:08
 * @ClassName: FtpConfig.class
 * @Description: 创建配置类FtpConfig
 */
@Data
@Component
@ConfigurationProperties(prefix = "ftp")
@PropertySource("classpath:ftp.properties")
public class FtpConfig {
    private String Host;
    private int Port;
    private String UserName;
    private String PassWord;
    private String workDir;
    private String encoding;
    private String root;
    private int MaxTotal;
    private int MinIdel;
    private int MaxIdle;
    private int MaxWaitMillis;
}
