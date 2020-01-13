/*
 * **************************************************************
 * Copyright ⓒ DONG.L PERSONAL DEVELOPMENT ,LTD.ALL
 * RIGHTS RESERVED.
 * **************************************************************
 * PROJECT INFORMATION:
 * 项目名称：spring-cloud-template
 * 文件名称：BaseEntity.java
 * 代码说明：实体基础属性
 * **************************************************************
 * CHANGE HISTORY:
 * Author Date Version Reason
 * Dong.L 2020/1/13 15:37 v1.0.0 初始创建
 *
 * **************************************************************
 */
package com.dongl.utils.model.entity;

import lombok.Data;

/**
 * @Description: 实体基础属性
 * @Project: com.dongl.utils.model.entity
 * @CreateDate: Created in 2020/1/13 15:37
 * @Author: Dong.L
 **/
@Data
public class BaseEntity {
    /**
     * 主键
     */
    private Long id;
    /**
     * 数据状态0-删除，1-正常
     */
    private Integer enabled;
    /**
     * 创建者
     */
    private String createdBy;
    /**
     * 更新者
     */
    private String updateBy;
    /**
     * 创建时间
     */
    private String createdTime;
    /**
     * 更新时间
     */
    private String updatedTime;
}
