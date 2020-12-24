/*
 * **************************************************************
 * Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * 项目名称：spring-cloud-template
 * 文件名称：JsonUtilsTest.java
 * 代码说明：JsonUtils测试类
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2020/12/24 18:26 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.dongl.utils;

import com.dongl.utils.util.JsonUtils;
import com.google.common.collect.ImmutableList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * @Description: JsonUtils测试类
 * @Project: com.dongl.utils
 * @CreateDate: Created in 2020/12/24 18:26
 * @Author: Dong.L
 **/
@RunWith(MockitoJUnitRunner.class)
public class JsonUtilsTest {

    @Test
    public void xmLTest() {
        JsonUtils.User.Student student = new JsonUtils.User.Student(88, "english");
        JsonUtils.User.Student student2 = new JsonUtils.User.Student(66, "language");
        JsonUtils.User user = new JsonUtils.User();
        user.setAge(11);
        user.setName("jack");
        user.setStudent(student);
        user.setStudents(ImmutableList.of(student2));

        String jsonXml = JsonUtils.objectToXml(user);
        System.out.println(jsonXml);
        String json = JsonUtils.objectToJson(user);
        System.out.println("objectToJson= " + json);
        json = JsonUtils.jsonToXml(json);
        System.out.println("jsonToXml= " + json);
        JsonUtils.User jsonUser = JsonUtils.xmlToObject(jsonXml, JsonUtils.User.class);
        System.out.println("xmlToJson= " + jsonUser);
    }
}
