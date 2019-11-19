package com.springboot.druid.mapper;

import com.springboot.druid.model.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author: Dong.L
 * @Create: 2019-09-30 10:14
 * @Description: java类描述
 */
public interface UserMapper extends BaseMapper<User> {

    User info(Long id);

    List<User> all();

    List<User> pageAll(@Param("condition") Map<String, Object> condition,
                       @Param("orderByClause") String orderByClause);
}
