/*
 * **************************************************************
 * Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * 项目名称：spring-cloud-template
 * 文件名称：UserVO.java
 * 代码说明：TODO
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2021/4/13 17:54 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.dongl.utils.model.demo;

import com.dongl.utils.model.demo.serizlizers.EmployTypeParser;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

/**
 * @Description: TODO
 * @Project: com.dongl.utils.model.demo
 * @CreateDate: Created in 2021/4/13 17:54
 * @Author: Dong.L
 **/
@Data
public class UserVO {

    @JsonProperty("user_name")
    private String userName;

    @JsonDeserialize(using = EmployTypeParser.Deserializer.class)
    @JsonSerialize(using = EmployTypeParser.Serializer.class)
    private EmployType type;

}
