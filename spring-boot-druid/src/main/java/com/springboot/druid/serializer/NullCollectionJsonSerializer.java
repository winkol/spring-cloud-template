package com.springboot.druid.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Collection;

/**
 * 集合对象、数组空json输出
 *
 * @author liwenjie
 * @date 2018/10/30
 */
public class NullCollectionJsonSerializer extends JsonSerializer<Collection> {

    @Override
    public void serialize(Collection value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartArray();
        jsonGenerator.writeEndArray();
    }
}