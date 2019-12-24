/*
 * **************************************************************
 * Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * 项目名称：spring-cloud-template
 * 文件名称：UserMapper.java
 * 代码说明：user dao层
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2019/12/24 10:38 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.springboot.sharding.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.sharding.entity.User;

/**
 * @Description: user dao层
 * @Project: com.springboot.sharding.mapper
 * @CreateDate: Created in 2019/12/24 10:38
 * @Author: Dong.L
 **/
public interface UserMapper extends BaseMapper<User> {
}
