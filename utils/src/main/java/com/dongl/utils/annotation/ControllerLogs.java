package com.dongl.utils.annotation;

import java.lang.annotation.*;

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
