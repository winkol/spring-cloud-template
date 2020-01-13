/*
 * **************************************************************
 * Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * 项目名称：Assert
 * 文件名称：Assert.java
 * 代码说明：参数判断
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2020/1/13 14:24 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.dongl.utils.util;

import com.dongl.utils.common.ApiCode;
import com.dongl.utils.exception.ParamIncorrectException;
import org.springframework.util.StringUtils;

/**
 * @Description: 参数判断
 * @Project: Assert
 * @CreateDate: Created in 2020/1/13 14:24
 * @Author: Dong.L
 */
public class Assert {
    private Assert() {
    }

    /**
     * @param object
     * @param errDesc
     * @method: notNull
     * @description: 判断参数是否为空
     * @return:
     * @throws:
     * @author: Dong.L
     * @date: 2020/1/13 14:28
     */
    public static void notNull(Object object, String errDesc) {
        if (StringUtils.isEmpty(object)) {
            throw new ParamIncorrectException(ApiCode.MISSING_PARAMETER, errDesc);
        }
    }

    /**
     * @param text
     * @param errDesc
     * @method: hasLength
     * @description: 长度判断
     * @return:
     * @throws:
     * @author: Dong.L
     * @date: 2020/1/13 14:27
     */
    public static void hasLength(String text, String errDesc) {
        if (!StringUtils.hasLength(text)) {
            throw new ParamIncorrectException(ApiCode.MISSING_PARAMETER, errDesc + "不能为Null,且必须包含字符");
        }
    }

    /**
     * @param text
     * @param errDesc
     * @method: hasText
     * @description: 内容是否为空
     * @return:
     * @throws:
     * @author: Dong.L
     * @date: 2020/1/13 14:27
     */
    public static void hasText(String text, String errDesc) {
        if (!StringUtils.hasText(text)) {
            throw new ParamIncorrectException(ApiCode.MISSING_PARAMETER, errDesc + "不能为Null，且必须包含非空白字符");
        }
    }
}
