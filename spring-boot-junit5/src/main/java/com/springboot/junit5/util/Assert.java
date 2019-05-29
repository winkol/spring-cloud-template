package com.springboot.junit5.util;

import com.springboot.junit5.exception.ParamIncorrectException;
import org.springframework.util.StringUtils;

/**
 * @Author: Dong.L
 * @Create: 2019-05-29 14:54
 * @Description: java类描述
 */
public class Assert {
    public static void notNull(Object object, String errDesc){
        if (StringUtils.isEmpty(object)) {
            throw new ParamIncorrectException(ResultCode.PARAM_ERROR, errDesc);
        }
    }

    public static void hasLength(String text, String errDesc) {
        if (!StringUtils.hasLength(text)) {
            throw new ParamIncorrectException(ResultCode.PARAM_ERROR, errDesc + "不能为Null,且必须包含字符");
        }
    }

    public static void hasText(String text, String errDesc) {
        if (!StringUtils.hasText(text)) {
            throw new ParamIncorrectException(ResultCode.PARAM_ERROR, errDesc + "不能为Null，且必须包含非空白字符");
        }
    }
}
