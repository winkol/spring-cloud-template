/*
 * **************************************************************
 * Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * 项目名称：spring-cloud-template
 * 文件名称：CusAccessObjectUtils.java
 * 代码说明：自定义访问对象工具类, 获取对象的IP地址等信息
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2020/1/13 14:29 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.dongl.utils.util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: 自定义访问对象工具类, 获取对象的IP地址等信息
 * @Project: com.dongl.utils.util
 * @CreateDate: Created in 2020/1/13 14:29
 * @Author: Dong.L
 **/
public class CusAccessObjectUtils {
    private final static String UNKNOWN = "unknown";

    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址,
     * <p>
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
     * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
     * <p>
     * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130, 192.168.1.100
     * <p>
     * 用户真实IP为： 192.168.1.110
     *
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (validateIp(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (validateIp(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (validateIp(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (validateIp(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (validateIp(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * @param ip
     * @method: validateIp
     * @description: 判断ip是否有值
     * @return:
     * @throws:
     * @author: Dong.L
     * @date: 2020/1/13 14:37
     */
    private static boolean validateIp(String ip) {
        return (ip == null || ip.length() <= 0 || UNKNOWN.equalsIgnoreCase(ip));
    }

    /**
     * 获取端口
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public static String doGet(HttpServletRequest request, HttpServletResponse response) {
        StringBuilder sb = new StringBuilder("->> 请求信息： ")
                // 返回请求行中的资源名称
                .append("uri: ").append(request.getRequestURI())
                // 获得客户端发送请求的完整url
                .append("url: ").append(request.getRequestURL().toString())
                // 返回发出请求的IP地址
                .append("ip: ").append(request.getRemoteAddr())
                // 返回请求行中的参数部分
                .append("params: ").append(request.getQueryString())
                // 返回发出请求的客户机的主机名
                .append("host: ").append(request.getRemoteHost())
                // 返回发出请求的客户机的端口号
                .append("port: ").append(request.getRemotePort());
        return sb.toString();
    }
}
