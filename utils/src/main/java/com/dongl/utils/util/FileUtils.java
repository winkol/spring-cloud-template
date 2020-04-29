/*
 * **************************************************************
 * Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * 项目名称：spring-cloud-template
 * 文件名称：FileUtils.java
 * 代码说明：TODO
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2020/4/20 10:14 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.dongl.utils.util;

import com.dongl.utils.configuration.HttpClientConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.Charsets;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Description: TODO
 * @Project: com.dongl.utils.util
 * @CreateDate: Created in 2020/4/20 10:14
 * @Author: Dong.L
 **/
@Slf4j
public class FileUtils {

    @Autowired
    private HttpClientConfig httpClientConfig;
    private CloseableHttpClient httpClient = null;
    private HttpPost httpPost = null;

    /**
     * @method: upload
     * @description: 文件上传，以流的形式
     * @param inputStream 文件流
     * @return:
     * @throws:
     * @author: Dong.L
     * @date: 2020/4/20 10:24
     */
    public void upload(InputStream inputStream) throws IOException {
        try {
            if (null == httpClient) {
                httpClient = httpClientConfig.getClient(true);
            }
            if (null == httpPost) {
                httpPost = httpClientConfig.getPostConfig("http://127.0.0.1:8080");
            }
            // 内容
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addTextBody("data", "{'tx_id':'aaa'}");
            builder.addBinaryBody("file", inputStream, ContentType.DEFAULT_BINARY, "F123");

            HttpEntity httpEntity = builder.build();
            httpPost.setEntity(httpEntity);

            CloseableHttpResponse response = httpClient.execute(httpPost);
            log.info("->> status: {}", response.getStatusLine());
            HttpEntity respEntity = response.getEntity();
            if (null == respEntity) {
                log.error("上传失败");
                return;
            }
            String sResponse = EntityUtils.toString(respEntity, Charsets.UTF_8);
            log.info("->> sResponse: {}", sResponse);
        } catch (Exception ex) {
            log.error("error: {}", "上传失败");
            log.error("error", ex);
        } finally {
            if (null != inputStream) {
                inputStream.close();
            }
        }
    }

}
