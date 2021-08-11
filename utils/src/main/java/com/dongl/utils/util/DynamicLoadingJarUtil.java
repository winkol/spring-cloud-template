/*
 * **************************************************************
 * Copyright ⓒ XIWEI PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * FILENAME：DynamicLoadingJarUtil.java
 * EXPLAIN：动态加载jar工具类
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2021/8/11 11:44 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.dongl.utils.util;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @Description: 动态加载jar工具类
 * @Project: com.dongl.utils.util
 * @CreateDate: Created in 2021/8/11 11:44
 * @Author: Dong.L
 **/
public class DynamicLoadingJarUtil {

    /**
     * java动态加载jar包，并运行其中的类和方法
     * RUL：https://blog.csdn.net/wawmg/article/details/17961815
     * @throws Exception
     */
    public static void loaderClassTwoTest() throws Exception {
        URL url = new URL("file:E:\\ld_work\\workspaces\\ideaIU\\spring-cloud-template\\out\\artifacts\\sgcc_model_jar\\sgcc-model.jar");
        URLClassLoader myClassLoader1 = new URLClassLoader(new URL[] { url }, Thread.currentThread()
                .getContextClassLoader());
        Class<?> myClass1 = myClassLoader1.loadClass("com.xiwei.model.ModelProcessImpl");
        // 当前项目存在class
//        IModelProcess process = (IModelProcess) myClass1.newInstance();
//        String str = process.process("v1.1");
//        System.out.println(str);
    }

    /**
     * java动态加载jar文件并执行方法
     * @throws Exception
     */
    public static void testLoadClass() throws Exception{
        String libPath ="E:\\ld_work\\workspaces\\ideaIU\\spring-cloud-template\\out\\artifacts\\sgcc_model_jar\\sgcc-model.jar";

        loadJar(libPath);
        Class<?> aClass = Class.forName("com.xiwei.model.ModelProcessImpl");
        Object instance = aClass.newInstance();
        Object strip = aClass.getDeclaredMethod("process", String.class).invoke(instance, "v20210811.0");
        System.out.println(strip);
    }
    public static void loadJar(String jarPath) {
        File jarFile = new File(jarPath);
        // 从URLClassLoader类中获取类所在文件夹的方法，jar也可以认为是一个文件夹
        Method method = null;
        try {
            method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
        } catch (NoSuchMethodException | SecurityException e1) {
            e1.printStackTrace();
        }
        //获取方法的访问权限以便写回
        boolean accessible = method.isAccessible();
        try {
            method.setAccessible(true);
            // 获取系统类加载器
            URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
            URL url = jarFile.toURI().toURL();
            method.invoke(classLoader, url);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            method.setAccessible(accessible);
        }
    }
}
