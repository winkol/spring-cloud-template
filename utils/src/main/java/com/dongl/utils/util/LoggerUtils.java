/*
 * **************************************************************
 * Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * 项目名称：spring-cloud-template
 * 文件名称：LoggerUtils.java
 * 代码说明：logger日志打印通用方法
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2020/12/17 10:48 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.dongl.utils.util;

import org.slf4j.Logger;
import org.slf4j.spi.LocationAwareLogger;

/**
 * @Description: logger日志打印通用方法
 * @Project: com.dongl.utils.util
 * @CreateDate: Created in 2020/12/17 10:48
 * @Author: Dong.L
 **/
public class LoggerUtils {
    private static final Object[] EMPTY_ARRAY = new Object[]{};
    private static final String FQCN = LoggerUtils.class.getName();

    private LoggerUtils() {
    }

    /**
     * @param logger 日志对象
     * @param format 日志格式
     * @method: trace
     * @description: trace 级别
     * @return:
     * @throws:
     * @author: Dong.L
     * @date: 2020/12/17 10:56
     */
    public static void trace(Logger logger, String format) {
        trace(logger, format, EMPTY_ARRAY);
    }

    /**
     * @param logger 日志对象
     * @param format 日志格式
     * @param args   日志参数
     * @method: trace
     * @description: trace 级别多参数
     * @return:
     * @throws:
     * @author: Dong.L
     * @date: 2020/12/17 11:00
     */
    public static void trace(Logger logger, String format, Object... args) {
        LocationAwareLogger awareLogger = getAwareLogger(logger);
        if (awareLogger.isTraceEnabled()) {
            awareLogger.log(null, FQCN, LocationAwareLogger.TRACE_INT, format, args, null);
        }
    }

    /**
     * @param logger 日志对象
     * @param format 日志格式
     * @method: debug
     * @description: debug 级别
     * @return:
     * @throws:
     * @author: Dong.L
     * @date: 2020/12/17 10:56
     */
    public static void debug(Logger logger, String format) {
        debug(logger, format, EMPTY_ARRAY);
    }

    /**
     * @param logger 日志对象
     * @param format 日志格式
     * @param args   日志参数
     * @method: debug
     * @description: debug 级别多参数
     * @return:
     * @throws:
     * @author: Dong.L
     * @date: 2020/12/17 11:00
     */
    public static void debug(Logger logger, String format, Object... args) {
        LocationAwareLogger awareLogger = getAwareLogger(logger);
        if (awareLogger.isDebugEnabled()) {
            awareLogger.log(null, FQCN, LocationAwareLogger.DEBUG_INT, format, args, null);
        }
    }

    /**
     * @param logger 日志对象
     * @param format 日志格式
     * @method: info
     * @description: info 级别
     * @return:
     * @throws:
     * @author: Dong.L
     * @date: 2020/12/17 10:56
     */
    public static void info(Logger logger, String format) {
        info(logger, format, EMPTY_ARRAY);
    }

    /**
     * @param logger 日志对象
     * @param format 日志格式
     * @param args   日志参数
     * @method: info
     * @description: info 级别多参数
     * @return:
     * @throws:
     * @author: Dong.L
     * @date: 2020/12/17 11:00
     */
    public static void info(Logger logger, String format, Object... args) {
        LocationAwareLogger awareLogger = getAwareLogger(logger);
        if (awareLogger.isInfoEnabled()) {
            awareLogger.log(null, FQCN, LocationAwareLogger.INFO_INT, format, args, null);
        }
    }

    /**
     * @param logger 日志对象
     * @param format 日志格式
     * @method: warn
     * @description: warn 级别
     * @return:
     * @throws:
     * @author: Dong.L
     * @date: 2020/12/17 10:56
     */
    public static void warn(Logger logger, String format) {
        warn(logger, format, EMPTY_ARRAY);
    }

    /**
     * @param logger 日志对象
     * @param format 日志格式
     * @param args   日志参数
     * @method: warn
     * @description: warn 级别多参数
     * @return:
     * @throws:
     * @author: Dong.L
     * @date: 2020/12/17 11:00
     */
    public static void warn(Logger logger, String format, Object... args) {
        LocationAwareLogger awareLogger = getAwareLogger(logger);
        if (awareLogger.isWarnEnabled()) {
            awareLogger.log(null, FQCN, LocationAwareLogger.WARN_INT, format, args, null);
        }
    }

    /**
     * @param logger 日志对象
     * @param format 日志格式
     * @method: error
     * @description: error 级别
     * @return:
     * @throws:
     * @author: Dong.L
     * @date: 2020/12/17 10:56
     */
    public static void error(Logger logger, String format) {
        error(logger, format, EMPTY_ARRAY);
    }

    /**
     * @param logger 日志对象
     * @param format 日志格式
     * @param args   日志参数
     * @method: error
     * @description: error 级别多参数
     * @return:
     * @throws:
     * @author: Dong.L
     * @date: 2020/12/17 11:00
     */
    public static void error(Logger logger, String format, Object... args) {
        LocationAwareLogger awareLogger = getAwareLogger(logger);
        if (awareLogger.isErrorEnabled()) {
            awareLogger.log(null, FQCN, LocationAwareLogger.ERROR_INT, format, args, null);
        }
    }

    /**
     * @param logger  日志对象
     * @param message 日志信息
     * @param ex      日志异常
     * @method: error
     * @description: error 级别多参数
     * @return:
     * @throws:
     * @author: Dong.L
     * @date: 2020/12/17 11:00
     */
    public static void error(Logger logger, String message, Throwable ex) {
        LocationAwareLogger awareLogger = getAwareLogger(logger);
        if (awareLogger.isErrorEnabled()) {
            awareLogger.log(null, FQCN, LocationAwareLogger.ERROR_INT, message, EMPTY_ARRAY, ex);
        }
    }

    /**
     * @param logger 日志对象
     * @method: getAwareLogger
     * @description: 获取适配日志器，供内部调用 修复打印行问题
     * @return: LocationAwareLogger
     * @throws:
     * @author: Dong.L
     * @date: 2020/12/17 10:52
     */
    private static LocationAwareLogger getAwareLogger(Logger logger) {
        return (LocationAwareLogger) logger;
    }
}
