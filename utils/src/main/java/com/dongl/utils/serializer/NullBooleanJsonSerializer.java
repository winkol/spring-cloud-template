package com.dongl.utils.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * Boolean空json输出
 *
 * @author liwenjie
 * @date 2018/10/30
 */
public class NullBooleanJsonSerializer extends JsonSerializer<Boolean> {

    @Override
    public void serialize(Boolean value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        // jsonGenerator.writeBoolean(false);
        jsonGenerator.writeNull();
    }
}