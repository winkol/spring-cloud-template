package com.springboot.pattern.annotation;
/*
 * **************************************************************
 * Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * 项目名称：spring-cloud-template
 * 文件名称：ReqType.java
 * 代码说明：TODO
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2020/5/20 16:20 v1.0.0 初始创建
 *
 * **************************************************************
 */

import com.springboot.pattern.enums.ReqTypeEnum;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: 类型
 * @Project: ReqType
 * @CreateDate: Created in 2020/5/20 16:23
 * @Author: Dong.L
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ReqType {

    ReqTypeEnum value();

}
