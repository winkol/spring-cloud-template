package com.springboot.apollo.util;

import java.util.Properties;

/**
 * @Author: Dong.L
 * @Create: 2019-10-31 10:41
 * @Description: 读取公共apollo配置
 */
public class PropertiesUtilsT {
    /**
     * apollo-common是我自己本地测试的一个公共配置，可有可无
     */
    public static final String COMMON = "apollo-common";
    public static final Properties properties = new Properties();

    public String getString(String key) {
        return properties.getProperty(key);
    }

    public Integer getInteger(String key) {
        try {
            return Integer.parseInt(properties.getProperty(key));
        } catch (Exception e) {
            return null;
        }
    }

    public Long getLong(String key) {
        try {
            return Long.parseLong(properties.getProperty(key));
        } catch (Exception e) {
            return null;
        }
    }

    public Boolean getBoolean(String key) {
        try {
            return Boolean.parseBoolean(properties.getProperty(key));
        } catch (Exception e) {
            return null;
        }
    }
}
