package com.springboot.druid.dao;

import com.springboot.druid.model.entity.UserJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: Dong.L
 * @Create: 2019-09-30 11:35
 * @Description: 用户服务数据接口类
 */
@Repository
public interface UserJpaDao extends JpaRepository<UserJpa, Long> {
}
