/*
 * **************************************************************
 * Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * 项目名称：spring-cloud-template
 * 文件名称：JsonUtils.java
 * 代码说明：http://www.manongjc.com/detail/14-mhqfhdfigizowlj.html
 * <!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind -->
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.9.5</version>
</dependency>

<!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-core -->
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-core</artifactId>
    <version>2.9.5</version>
</dependency>

<!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-annotations -->
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-annotations</artifactId>
    <version>2.9.5</version>
</dependency>
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2020/11/12 10:06 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.dongl.utils.util;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @Description: Java使用ObjectMapper做数据转换的常用工具类实现
 *  https://www.php.cn/xml_rss-353174.html
 * @Project: com.dongl.utils.util
 * @CreateDate: Created in 2020/11/12 10:06
 * @Author: Dong.L
 **/
public class JsonUtils {
    private static final Logger log = LoggerFactory.getLogger(JsonUtils.class);

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final XmlMapper XMLMAPPER = new XmlMapper();

    private JsonUtils() {
    }

    static {
        //序列化的时候序列对象的所有属性
        MAPPER.setSerializationInclusion(Include.ALWAYS);

        //反序列化的时候如果多了其他属性,不抛出异常
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        //如果是空对象的时候,不抛异常
        MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        //取消时间的转化格式,默认是时间戳,可以取消,同时需要设置要表现的时间格式
//        MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
//        MAPPER.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * @param data data
     * @method: objectToJson
     * @description: 对象转为Json字符串
     * @return: String
     * @throws:
     * @author: Dong.L
     * @date: 2020/12/17 14:17
     */
    public static String objectToJson(Object data) {
        if (validDataIsNull(data, "objectToJson")) {
            return null;
        }
        try {
            return MAPPER.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            LoggerUtils.warn(log, "->> object to json fail, data: {}", data);
            LoggerUtils.debug(log, "->> object to json fail, {}", e);
        }
        return null;
    }

    /**
     * @param data data
     * @method: objectToBytes
     * @description: 对象转为byte数组
     * @return: byte
     * @throws:
     * @author: Dong.L
     * @date: 2020/12/17 14:19
     */
    public static byte[] objectToBytes(Object data) {
        if (validDataIsNull(data, "objectToBytes")) {
            return null;
        }
        try {
            return MAPPER.writeValueAsBytes(data);
        } catch (JsonProcessingException e) {
            LoggerUtils.warn(log, "->> object to bytes fail, data: {}", data);
            LoggerUtils.debug(log, "->> object to bytes fail, {}", e);
        }
        return null;
    }

    /**
     * @param byteArr   byteArr
     * @param valueType valueType
     * @method: bytesToObject
     * @description: byte数组转为对象
     * @return: T
     * @throws:
     * @author: Dong.L
     * @date: 2020/12/17 14:25
     */
    public static <T> T bytesToObject(byte[] byteArr, Class<T> valueType) {
        if (validDataIsNull(byteArr, "bytesToObject")) {
            return null;
        }
        try {
            return MAPPER.readValue(byteArr, valueType);
        } catch (Exception e) {
            LoggerUtils.warn(log, "->> bytes to object fail, byteArr: {}", byteArr);
            LoggerUtils.debug(log, "->> bytes to object fail, {}", e);
        }
        return null;
    }

    /**
     * @param data      data
     * @param valueType valueType
     * @method: jsonToObject
     * @description: json字符串转为对象
     * @return: T
     * @throws:
     * @author: Dong.L
     * @date: 2020/12/17 14:20
     */
    public static <T> T jsonToObject(String data, Class<T> valueType) {
        if (validDataIsNull(data, "jsonToObject")) {
            return null;
        }
        try {
            return MAPPER.readValue(data, valueType);
        } catch (Exception e) {
            LoggerUtils.warn(log, "->> json to object fail, data: {}", data);
            LoggerUtils.debug(log, "->> json to object fail, {}", e);
        }
        return null;
    }

    /**
     * @param data      data
     * @param valueType valueType
     * @method: objectToObject
     * @description: 对象转对象
     * @return: T
     * @throws:
     * @author: Dong.L
     * @date: 2020/12/17 14:30
     */
    public static <T> T objectToObject(Object data, Class<T> valueType) {
        if (validDataIsNull(data, "objectToObject")) {
            return null;
        }
        try {
            return MAPPER.convertValue(data, valueType);
        } catch (Exception e) {
            LoggerUtils.warn(log, "->> object to object fail, data: {}", data);
            LoggerUtils.debug(log, "->> object to object fail, {}", e);
        }
        return null;
    }

    /**
     * @param data data
     * @method: jsonToMap
     * @description: json转map
     * @return: Map
     * @throws:
     * @author: Dong.L
     * @date: 2020/12/17 14:33
     */
    public static Map jsonToMap(String data) {
        if (validDataIsNull(data, "jsonToMap")) {
            return Collections.emptyMap();
        }
        try {
            return MAPPER.readValue(data, Map.class);
        } catch (Exception e) {
            LoggerUtils.warn(log, "->> json to map fail, data: {}", data);
            LoggerUtils.debug(log, "->> json to map fail, {}", e);
        }
        return Collections.emptyMap();
    }

    /**
     * @param data      data
     * @param valueType valueType
     * @method: jsonToList
     * @description: json字符串转list
     * @return: List
     * @throws:
     * @author: Dong.L
     * @date: 2020/12/17 14:36
     */
    public static <T> List<T> jsonToList(String data, Class<T> valueType) {
        if (validDataIsNull(data, "jsonToList")) {
            return Collections.emptyList();
        }
        try {
            JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, valueType);
            return MAPPER.readValue(data, javaType);
        } catch (Exception e) {
            LoggerUtils.warn(log, "->> json to list fail, data: {}", data);
            LoggerUtils.debug(log, "->> json to list fail, error: {}", e);
        }
        return Collections.emptyList();
    }

    /**
     * @param data data
     * @method: isArray
     * @description: 判断json字符串是否是数组
     * @return: Boolean
     * @throws:
     * @author: Dong.L
     * @date: 2020/12/18 9:50
     */
    public static Boolean isArray(String data) {
        if (validDataIsNull(data, "isArray")) {
            return null;
        }
        try {
            JsonNode jsonNode = MAPPER.readValue(data, JsonNode.class);
            return jsonNode.isArray();
        } catch (Exception e) {
            LoggerUtils.warn(log, "isArray fail, data: {}", data);
            LoggerUtils.debug(log, "isArray fail, error: {}", e);
        }
        return null;
    }

    /**
     * @param data 转换json数据
     * @method: jsonToXml
     * @description: json转xml
     * @return: String
     * @throws:
     * @author: Dong.L
     * @date: 2020/12/24 19:52
     */
    public static String jsonToXml(String data) {
        if (validDataIsNull(data, "jsonToXml")) {
            return null;
        }
        try {
            JsonNode root = MAPPER.readTree(data);
            return XMLMAPPER.writeValueAsString(data);
        } catch (Exception e) {
            LoggerUtils.warn(log, "isArray fail, data: {}", data);
            LoggerUtils.debug(log, "isArray fail, error: {}", e);
        }
        return null;
    }

    /**
     * @param data 转换对象数据
     * @method: objectToXml
     * @description: 对象转xml
     * @return: String
     * @throws:
     * @author: Dong.L
     * @date: 2020/12/24 19:53
     */
    public static String objectToXml(Object data) {
        if (validDataIsNull(data, "objectToXml")) {
            return null;
        }
        try {
            return XMLMAPPER.writeValueAsString(data);
        } catch (Exception e) {
            LoggerUtils.warn(log, "isArray fail, data: {}", data);
            LoggerUtils.debug(log, "isArray fail, error: {}", e);
        }
        return null;
    }

    /**
     * @param data      转换json字符串
     * @param valueType 转换的对象
     * @method: xmlToObject
     * @description: xml转对象
     * @return: T
     * @throws:
     * @author: Dong.L
     * @date: 2020/12/24 19:54
     */
    public static <T> T xmlToObject(String data, Class<T> valueType) {
        if (validDataIsNull(data, "xmlToObject")) {
            return null;
        }
        try {
            return XMLMAPPER.readValue(data, valueType);
        } catch (Exception e) {
            LoggerUtils.warn(log, "xmlToJson fail, data: {}", data);
            LoggerUtils.debug(log, "xmlToJson fail, error: {}", e);
        }
        return null;
    }

    @Data
    public static class User {
        private Integer age;
        private String name;
        private Student student;
        private List<Student> students;

        public User() {
        }

        @Data
        public static class Student {
            private Integer grade;
            private String subject;

            public Student() {
            }

            public Student(Integer grade, String subject) {
                this.grade = grade;
                this.subject = subject;
            }
        }
    }

    /**
     * @param data        判断对象
     * @param fileMessage 提示
     * @method: validDataIsNull
     * @description: 验证转换参数
     * @return: Boolean
     * @throws:
     * @author: Dong.L
     * @date: 2020/12/24 19:51
     */
    private static Boolean validDataIsNull(Object data, String fileMessage) {
        if (StringUtils.isEmpty(data)) {
            LoggerUtils.warn(log, "{} fail, data is null", fileMessage);
            LoggerUtils.debug(log, "{} fail, data is null", fileMessage);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
