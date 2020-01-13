/*
* **************************************************************
* Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
* RIGHTS RESERVED.
* **************************************************************
* PROJECT INFORMATION:
* 项目名称：ServletRequestAttributesUtils
* 文件名称：ServletRequestAttributesUtils.java
* 代码说明：获取HttpServletRequest、HttpServletResponse、HttpSession工类具
* **************************************************************
* CHANGE HISTORY:
* Author Date Version Reason
* Dong.L 2020/1/13 14:33 v1.0.0 初始创建
*
* **************************************************************
*/
package com.dongl.utils.util;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Description: 获取HttpServletRequest、HttpServletResponse、HttpSession工类具
 * @Project: ServletRequestAttributesUtils
 * @CreateDate: Created in 2020/1/13 14:32
 * @Author: Dong.L
 */
public final class ServletRequestAttributesUtils {

    private ServletRequestAttributesUtils() {

    }

    public static HttpServletRequest getHttpServletRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        return servletRequestAttributes.getRequest();
    }

    public static HttpServletResponse getHttpServletResponse() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        return servletRequestAttributes.getResponse();
    }

    public static HttpSession getHttpSession() {
        HttpServletRequest request = getHttpServletRequest();
        return request.getSession();
    }
}