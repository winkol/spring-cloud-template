package com.dongl.utils.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @Description: 公共Mapper
 * @Project: BaseMapper
 * @CreateDate: Created in 2020/1/13 15:25
 * @Author: Dong.L
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {

}
