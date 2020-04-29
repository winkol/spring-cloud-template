package com.springboot.rabbitmq.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Author:  Dong.L
 * @Date:    2019/11/19 15:06
 * @Description: ObjectMapper工具类
 */
@Slf4j
@Component
public final class ObjectMapperUtils {

    @Autowired
    private ObjectMapper objectMapper;

    private static ObjectMapper mapper;

    private ObjectMapperUtils() {

    }

    @PostConstruct
    public void init() {
        mapper = objectMapper;
    }

    /**
     * 对象转字符串
     *
     * @param value
     * @return
     */
    public static String writeValueAsString(Object value) {
        try {
            return mapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            log.error("value={}", value);
            log.error("对象转字符串失败", e);
        }
        log.error("对象转字符串失败");
        return null;
    }


    /**
     * 对象转对象
     *
     * @param fromValue
     * @param toValueType
     * @return
     */
    public static <T> T convertValue(Object fromValue, Class<T> toValueType) {
        return mapper.convertValue(fromValue, toValueType);
    }

    /**
     * 字符串转对象
     *
     * @param fromValue
     * @param toValueType
     * @param <T>
     * @return
     */
    public static <T> List<T> readValueList(String fromValue, Class<T> toValueType) {
        JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, toValueType);
        try {
            return (List<T>) mapper.readValue(fromValue, javaType);
        } catch (IOException e) {
            log.error("fromValue={}", fromValue);
            log.error("字符串转对象", e);
        }
        log.error("json转换失败");
        return null;
    }

    /**
     * json转map
     *
     * @param input
     * @return
     * @throws IOException
     */
    public static Map<String, Object> jsonToMap(String input) {
        try {
            return mapper.readValue(input, Map.class);
        } catch (IOException e) {
            log.error("input={}", input);
            log.error("json转map失败", e);
        }
        log.error("json转换失败");
        return null;
    }

    /**
     * json转对象
     *
     * @param input
     * @return
     * @throws IOException
     */
    public static <T> T readValue(String input, Class<T> tClass) {
        try {
            return mapper.readValue(input, tClass);
        } catch (IOException e) {
            log.error("input={}", input);
            log.error("json转对象失败", e);
        }
        log.error("json转换失败");
        return null;
    }
}
