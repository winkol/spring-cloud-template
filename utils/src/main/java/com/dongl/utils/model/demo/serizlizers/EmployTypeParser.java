/*
 * **************************************************************
 * Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * 项目名称：spring-cloud-template
 * 文件名称：EmployTypeParser.java
 * 代码说明：TODO
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2021/4/13 17:57 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.dongl.utils.model.demo.serizlizers;

import com.dongl.utils.model.demo.EmployType;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @Description: TODO
 * @Project: com.dongl.utils.model.demo.serizlizers
 * @CreateDate: Created in 2021/4/13 17:57
 * @Author: Dong.L
 **/
public class EmployTypeParser {

    public static class Serializer extends JsonSerializer<EmployType> {
        @Override
        public void serialize(EmployType employType, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeString(employType.getName());
        }
    }

    public static class Deserializer extends JsonDeserializer<EmployType> {
        @Override
        public EmployType deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            return EmployType.fromName(jsonParser.getValueAsString());
        }
    }
}
