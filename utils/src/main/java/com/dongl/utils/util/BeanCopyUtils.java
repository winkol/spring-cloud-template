/*
 * **************************************************************
 * Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * 项目名称：spring-cloud-template
 * 文件名称：BeanCopyUtils.java
 * 代码说明：TODO
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2020/12/17 14:40 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.dongl.utils.util;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.cglib.beans.BeanCopier;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description: bean复制工具类
 * @Project: com.dongl.utils.util
 * @CreateDate: Created in 2020/12/17 14:40
 * @Author: Dong.L
 **/
public class BeanCopyUtils {

    /**
     * @param source 源数据对象
     * @param target 目标据对象
     * @method: objectCopyObject
     * @description: 对象属性复制
     * @return:
     * @throws:
     * @author: Dong.L
     * @date: 2020/12/17 14:45
     */
    public static void objectCopyObject(Object source, Object target) {
        BeanCopier beanCopier = BeanCopier.create(source.getClass(), target.getClass(), false);
        beanCopier.copy(source, target, null);
    }

    /**
     * @param source     源数据对象
     * @param target     目标据对象
     * @param ignoreNull 是否忽略非空
     * @method: copyProperties
     * @description: 拷贝对象属性值空或非空属性
     * @return:
     * @throws:
     * @author: Dong.L
     * @date: 2020/12/17 14:46
     */
    public static void copyProperties(Object source, Object target, Boolean ignoreNull) {
        if (ignoreNull) {
            BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
        } else {
            BeanUtils.copyProperties(source, target);
        }
    }

    /**
     * @param source 要拷贝的对象
     * @method: getNullPropertyNames
     * @description: 获取到对象中属性为null的属性名
     * @return:
     * @throws:
     * @author: Dong.L
     * @date: 2020/12/17 14:51
     */
    private static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();
        Set<String> emptyNames = new HashSet<>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (null == srcValue) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

}
