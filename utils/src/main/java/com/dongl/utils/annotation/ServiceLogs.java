package com.dongl.utils.annotation;

import java.lang.annotation.*;

/**
 * @Author: Dong.L
 * @Create: 2019-07-19 16:39
 * @Description: Service记录
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ServiceLogs {
    /**
     * 描述
     * @return
     */
    String description() default "";
}
