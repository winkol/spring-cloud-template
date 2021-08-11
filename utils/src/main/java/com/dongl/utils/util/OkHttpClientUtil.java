/*
 * **************************************************************
 * Copyright ⓒ XIWEI PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * FILENAME：OkHttpClientUtil.java
 * EXPLAIN：创建线程安全的okhttp单例
 * URL: https://www.cnblogs.com/Eric-zhao/p/11561578.html
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2021/8/9 11:54 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.dongl.utils.util;

import okhttp3.ConnectionPool;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 创建线程安全的okhttp单例
 *  URL: https://www.cnblogs.com/Eric-zhao/p/11561578.html
 *  较全的 OkHttpClient工具
 *  URL：https://blog.csdn.net/u013256816/article/details/111350854
 * @Project: com.xiwei.gwx.sgcc.service.util
 * @CreateDate: Created in 2021/8/9 11:54
 * @Author: Dong.L
 **/
public class OkHttpClientUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(OkHttpClientUtil.class);
    private static final int CONNECTION_TIME_OUT = 2000;//连接超时时间
    private static final int SOCKET_TIME_OUT = 2000;//读写超时时间
    private static final int MAX_IDLE_CONNECTIONS = 30;// 空闲连接数
    private static final long KEEP_ALLIVE_TIME = 60000L;//保持连接时间

    private OkHttpClient okHttpClient;
    private volatile static OkHttpClientUtil httpUtils;

    public static OkHttpClientUtil getInstance() {
        if (httpUtils == null) {
            synchronized (OkHttpClientUtil.class) {
                if (httpUtils == null) {
                    httpUtils = new OkHttpClientUtil();
                }
            }
        }
        return httpUtils;
    }

    public OkHttpClientUtil() {
        ConnectionPool connectionPool = new ConnectionPool(MAX_IDLE_CONNECTIONS, KEEP_ALLIVE_TIME, TimeUnit.MILLISECONDS);
        this.okHttpClient = new OkHttpClient()
                .newBuilder()
                .readTimeout(SOCKET_TIME_OUT, TimeUnit.MILLISECONDS)
                .writeTimeout(SOCKET_TIME_OUT, TimeUnit.MILLISECONDS)
                .connectionPool(connectionPool)
                .retryOnConnectionFailure(false) //自动重连设置为false
                .connectTimeout(CONNECTION_TIME_OUT, TimeUnit.MILLISECONDS) //连接超时时间
                .addInterceptor(new RetryIntercepter(2)) //重试拦截器2次
                .addNetworkInterceptor(new NetworkInterceptor()) //网络拦截器，统一打印日志
                .build();
    }

    /**
     * post发送带url参数的json
     */
    public String post(String url, Map<String,String> pathParams, String body){
        RequestBody requestBody = RequestBody.create(body, MediaType.parse("application/json;charset=utf-8"));
        HttpUrl.Builder builder = HttpUrl.parse(url).newBuilder();
        for(String key:pathParams.keySet()){
            builder.addQueryParameter(key,pathParams.get(key) );
        }
        Request request = new Request.Builder()
                .post(requestBody)
                .url(builder.build().toString())
                .build();
        return execute(request);
    }

    /**
     * 发送待url参数的get
     */
    public String get(String url, Map<String,String> pathParams){
        HttpUrl.Builder builder = HttpUrl.parse(url).newBuilder();
        for(String key:pathParams.keySet()){
            builder.addQueryParameter(key,pathParams.get(key) );
        }
        Request request = new Request.Builder()
                .url(builder.build().toString())
                .build();
        return execute(request);
    }

    private String execute(Request request){
        String responseBody=null;
        try {
            Response response = okHttpClient.newCall(request).execute();
            responseBody = response.body().string();
        } catch (IOException |NullPointerException e) {
            e.printStackTrace();
        }
        return responseBody;
    }

    /**
     * 重试拦截器
     */
    public static class RetryIntercepter implements Interceptor {
        public int maxRetryCount;
        private int count = 0;

        public RetryIntercepter(int maxRetryCount) {
            this.maxRetryCount = maxRetryCount;
        }

        @NotNull
        @Override
        public Response intercept(@NotNull Chain chain) {
            return retry(chain);
        }

        public Response retry(Chain chain) {
            Response response = null;
            Request request = chain.request();
            try {
                response = chain.proceed(request);
                while (!response.isSuccessful() && count < maxRetryCount) {
                    count++;
                    response = retry(chain);
                }
            } catch (Exception e) {
                while (count < maxRetryCount) {
                    count++;
                    response = retry(chain);
                }
            }
            return response;
        }
    }

    /**
     * 网络拦截器，打印请求、响应时间、响应状态码，响应内容
     */
    public static class NetworkInterceptor implements Interceptor {

        @NotNull
        @Override
        public Response intercept(@NotNull Chain chain) {
            long start = System.currentTimeMillis();
            Response response = null;
            String responseBody = null;
            String responseCode = null;
            String url = null;
            String requestBody = null;
            try {
                Request request = chain.request();
                url = request.url().toString();
                requestBody = getRequestBody(request);
                response = chain.proceed(request);
                responseBody = Objects.requireNonNull(response.body()).string();
                responseCode = String.valueOf(response.code());
                MediaType mediaType = Objects.requireNonNull(response.body()).contentType();
                response = response.newBuilder().body(ResponseBody.create(responseBody, mediaType)).build();
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            } finally {
                long end = System.currentTimeMillis();
                String duration = String.valueOf(end - start);
                LOGGER.info("responseTime= {}, requestUrl= {}, params={}, responseCode= {}, result= {}",
                        duration, url, requestBody, responseCode, responseBody);
            }

            assert response != null;
            return response;
        }

        private String getRequestBody(Request request) {
            String requestContent = "";
            if (request == null) {
                return requestContent;
            }
            RequestBody requestBody = request.body();
            if (requestBody == null) {
                return requestContent;
            }
            try {
                Buffer buffer = new Buffer();
                requestBody.writeTo(buffer);
                Charset charset = StandardCharsets.UTF_8;
                requestContent = buffer.readString(charset);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return requestContent;
        }
    }
}
