package com.springboot.junit5.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

/**
 * @Author: Dong.L
 * @Create: 2019-05-29 9:40
 * @Description: 属性文件获取工具类（仅json）
 */
@Slf4j
public class PropertiesUtils {
    /**
     * 根据json文件名称获取json配置文件数据
     * @param fileName-json文件名称前缀，如果在resource下直接写文件名，如果有路径前面加路径:com/spring/xx
     * @return
     */
    public static JSONObject getJsonResource(String fileName) {
        fileName += ".json";
        ClassLoader classLoader = getClassLoader();
        Enumeration<URL> resources;
        JSONObject jsonObject = new JSONObject();
        try {
            resources = classLoader.getResources(fileName);
        } catch (IOException ex) {
            log.warn("getJsonResource fail {}", fileName, ex);
            return jsonObject;
        }
        while (resources.hasMoreElements()){
            URL url = resources.nextElement();
            try {
                log.info("->> urlPath: {}", url.getPath());
                String json = Resources.toString(url, Charsets.UTF_8);
                log.info("->> json: {}", json);
                jsonObject.putAll(JSON.parseObject(json));
            } catch (IOException ex) {
                log.warn("addJsonFile fail url: {}", url, ex);
            }
        }
        return jsonObject;
    }

    /**
     * 根据json文件名称获取json配置数据2
     * @param fileName-
     * @return
     */
    public static JSONObject getJsonResource2(String fileName) {
        fileName = String.format("classpath:%s%s", fileName, ".json");
        log.info("->> fileName: {}", fileName);
        JSONObject jsonObject = new JSONObject();
        try {
            File jsonFile = ResourceUtils.getFile(fileName);
            String json = FileUtils.readFileToString(jsonFile, "UTF-8");
            jsonObject.putAll(JSON.parseObject(json));
        } catch (IOException ex) {
            log.warn("getJsonResource fail {}", fileName, ex);
            return jsonObject;
        }
        return jsonObject;
    }

    private static ClassLoader getClassLoader(){
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if (classLoader != null) {
            return classLoader;
        }
        return PropertiesUtils.class.getClassLoader();
    }

    private PropertiesUtils(){
        throw new IllegalAccessError();
    }

}
