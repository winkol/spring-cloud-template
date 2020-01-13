package com.dongl.utils.serializer;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * json输出空处理
 *
 * @author liwenjie
 * @date 2018/10/30
 */
public class WebBeanSerializerModifier extends BeanSerializerModifier {
    private static final JsonSerializer _nullStringJsonSerializer = new NullStringJsonSerializer();
    private static final JsonSerializer _mullNumberJsonSerializer = new NullNumberJsonSerializer();
    private static final JsonSerializer _nullBooleanJsonSerializer = new NullBooleanJsonSerializer();
    private static final JsonSerializer _nullCollectionJsonSerializer = new NullCollectionJsonSerializer();

    private static final Class[] stringClass = {String.class};
    private static final Class[] booleanClass = {Boolean.class, boolean.class};
    private static final Class[] collectionClass = {List.class, Map.class, Array.class, Set.class};
    private static final Class[] numberClass = {Long.class, Integer.class, BigDecimal.class, Double.class, Float.class,
            long.class, int.class, double.class, float.class};

    @Override
    public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription beanDesc, List<BeanPropertyWriter> beanProperties) {
        beanProperties.forEach(writer -> {
            if (isString(writer)) {
                writer.assignNullSerializer(this._nullStringJsonSerializer);
            } /*else if (isNumber(writer)) {
                writer.assignNullSerializer(this._mullNumberJsonSerializer);
            } else if (isBoolean(writer)) {
                writer.assignNullSerializer(this._nullBooleanJsonSerializer);
            } else if (isCollection(writer)) {
                writer.assignNullSerializer(this._nullCollectionJsonSerializer);
            }*/
        });
        return beanProperties;
    }

    private boolean isString(BeanPropertyWriter writer) {
        return isTrue(writer, stringClass);
    }

    private boolean isBoolean(BeanPropertyWriter writer) {
        return isTrue(writer, booleanClass);
    }

    private boolean isNumber(BeanPropertyWriter writer) {
        return isTrue(writer, numberClass);
    }

    private boolean isCollection(BeanPropertyWriter writer) {
        return isTrue(writer, collectionClass);
    }

    private boolean isTrue(BeanPropertyWriter writer, Class<?>[] clazzs) {
        boolean flag = false;
        for (Class<?> clazz : clazzs) {
            JavaType javaType = writer.getType();
            JavaType superType = javaType.findSuperType(clazz);
            if (superType != null) {
                flag = true;
                break;
            }
        }
        return flag;
    }
}