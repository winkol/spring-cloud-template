package com.springboot.druid.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: Dong.L
 * @Create: 2019-07-19 16:42
 * @Description: Controller日志记录
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ControllerLogs {
    /**
     * 描述
     * @return
     */
    String description() default "";
}
