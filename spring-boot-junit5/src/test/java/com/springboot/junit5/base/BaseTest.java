package com.springboot.junit5.base;

import com.alibaba.fastjson.JSONObject;
import com.springboot.junit5.mode.RequestDataVO;
import com.springboot.junit5.mode.ResponseDataVO;
import com.springboot.junit5.util.PropertiesUtils;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @Author: Dong.L
 * @Create: 2019-05-27 11:41
 * @Description: java类描述
 */
@Slf4j
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public abstract class BaseTest {
    protected String baseUrl;

    /**
     * 获取请求json数据
     * @param fileName-
     * @return
     */
    protected RequestDataVO requestDataHandle(String fileName){
        JSONObject jsonObject = PropertiesUtils.getJsonResource(fileName);
        if (null == jsonObject) {
            throw new ParameterResolutionException("参错误");
        }
        RequestDataVO requestDataVO = jsonObject.toJavaObject(RequestDataVO.class);
        if (null == requestDataVO) {
            throw new ParameterResolutionException("参数转换错误");
        }
        return requestDataVO;
    }

    protected void responseHandle(ResponseEntity<ResponseDataVO> responseEntity) {
        log.info("->> responseHandle: {}", responseEntity.toString());
        Assertions.assertThat(responseEntity).as("非空判断").isNotNull();
        Assertions.assertThat(responseEntity.getStatusCode()).as("响应状态码判断").isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody()).as("响应结果判断").isNotNull();
        log.info("->> body: {}", responseEntity.getBody());
        ResponseDataVO responseDataVO = responseEntity.getBody();
        log.info("->> responseDataVO: {}", responseDataVO.toString());
    }
}
