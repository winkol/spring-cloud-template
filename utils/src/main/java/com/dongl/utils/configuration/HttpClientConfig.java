/*
 * **************************************************************
 * Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * 项目名称：spring-cloud-template
 * 文件名称：HttpClientUtil.java
 * 代码说明：TODO
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2020/4/17 09:36 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.dongl.utils.configuration;

import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLException;
import java.net.UnknownHostException;

/**
 * @Description: HttpClient初始化
 * @Project: com.dongl.utils.util
 * @CreateDate: Created in 2020/4/17 09:36
 * @Author: Dong.L
 **/
@Component
public class HttpClientConfig {
    /**
     * 重试次数
     **/
    @Value("${httpclient.retry.num:3}")
    private Integer retryNum;

    private final RequestConfig requestConfig;
    private final PoolingHttpClientConnectionManager connectionManager;
    private final ConnectionKeepAliveStrategy keepAliveStrategy;

    public HttpClientConfig(@Qualifier("requestConfigTimeout") RequestConfig requestConfig,
                            @Qualifier("poolingHCCM") PoolingHttpClientConnectionManager connectionManager,
                            @Qualifier("keepAliveStrategy") ConnectionKeepAliveStrategy keepAliveStrategy) {
        this.requestConfig = requestConfig;
        this.connectionManager = connectionManager;
        this.keepAliveStrategy = keepAliveStrategy;
    }

    /**
     * @param uri
     * @method: getPostConfig
     * @description: httpPost初始
     * @return: HttpPost
     * @throws:
     * @author: Dong.L
     * @date: 2020/4/20 9:55
     */
    public HttpPost getPostConfig(String uri) {
        HttpPost httpPost = new HttpPost(uri);
        httpPost.setConfig(requestConfig);
        return httpPost;
    }

    /**
     * @param isPooled 是否使用连接池
     * @method: getClient
     * @description: TODO
     * @return: CloseableHttpClient
     * @throws:
     * @author: Dong.L
     * @date: 2020/4/20 10:03
     */
    public CloseableHttpClient getClient(boolean isPooled) {
        HttpRequestRetryHandler retryHandler = (exception, executionCount, context) -> {
            if (executionCount >= retryNum) {
                // 最大重试次数
                return false;
            }
            /*
                UnknownHostException-目标服务器不可达
                ConnectTimeoutException-连接被拒绝
                SSLException-SSl扬异常
                NoHttpResponseException-如果服务器丢掉了连接，那就重试
             */
            if (exception instanceof UnknownHostException || exception instanceof ConnectTimeoutException
                    || !(exception instanceof SSLException) || exception instanceof NoHttpResponseException) {
                return true;
            }
            HttpClientContext clientContext = HttpClientContext.adapt(context);
            HttpRequest request = clientContext.getRequest();
            boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
            if (idempotent) {
                return true;
            }
            return false;
        };
        if (isPooled) {
            return HttpClients.custom().setKeepAliveStrategy(keepAliveStrategy)
                    .setConnectionManager(connectionManager)
                    .setRetryHandler(retryHandler)
                    .build();
        }
        return HttpClients.createDefault();
    }
}
