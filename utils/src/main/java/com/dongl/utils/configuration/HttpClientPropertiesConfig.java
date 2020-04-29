/*
 * **************************************************************
 * Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * 项目名称：spring-cloud-template
 * 文件名称：HttpClientPropertiesConfig.java
 * 代码说明：TODO
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2020/4/20 09:33 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.dongl.utils.configuration;

import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: TODO
 * @Project: com.dongl.utils.configuration
 * @CreateDate: Created in 2020/4/20 09:33
 * @Author: Dong.L
 **/
@Configuration
public class HttpClientPropertiesConfig {
    /**读取超时**/
    @Value("${httpclient.sockettimeout}")
    private Integer socketTimeout;
    /**请求超时**/
    @Value("${httpclient.connectTimeout}")
    private Integer connectTimeout;
    /**连接超时**/
    @Value("${httpclient.connectionRequestTimeout}")
    private Integer connectionRequestTimeout;
    /**整个池子大小**/
    @Value("${httpclient.maxTotal}")
    private Integer maxTotal;
    @Value("${httpclient.defaultMaxPerRout}")
    private Integer defaultMaxPerRout;
    /**连接时长**/
    @Value("${httpclient.keepAlive}")
    private Integer keepAlive;

    @Bean(name = "requestConfigTimeout")
    public RequestConfig initRequestConfig(){
        return RequestConfig.custom()
                .setSocketTimeout(socketTimeout)
                .setConnectTimeout(connectTimeout)
                .setConnectionRequestTimeout(connectionRequestTimeout)
                .build();
    }

    @Bean(name = "poolingHCCM")
    public PoolingHttpClientConnectionManager initPoolingManager(){
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(maxTotal);
        connectionManager.setDefaultMaxPerRoute(defaultMaxPerRout);
        return connectionManager;
    }

    @Bean(name = "keepAliveStrategy")
    public ConnectionKeepAliveStrategy initKeepAliveStrategy(){
        return new DefaultConnectionKeepAliveStrategy(){
            @Override
            public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
                long sKeepAlive = super.getKeepAliveDuration(response, context);
                if(sKeepAlive ==-1) {
                    // 如果服务器没设置keep-alive参数，这里默认设置1分钟
                    sKeepAlive = keepAlive;
                }
                return sKeepAlive;
            }
        };
    }

}
