package com.springboot.druid.configuration;

import com.alibaba.druid.filter.config.ConfigTools;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @Author: Dong.L
 * @Create: 2019-11-15 15:58
 * @Description: 数据库加载方式 2
 */
@Slf4j
public class DataSourceConfig {

    @Autowired
    private DataSourceSettingsProperties properties;

    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean reg = new ServletRegistrationBean();
        reg.setServlet(new StatViewServlet());
        reg.addUrlMappings("/druid/*");
        reg.addInitParameter("loginUsername", properties.getUsername());
        reg.addInitParameter("loginPassword", properties.getPassword());
        return reg;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        filterRegistrationBean.addInitParameter("profileEnable", "true");
        return filterRegistrationBean;
    }

    @Bean
    @Primary
    public DataSource druidDataSource() throws Exception {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(properties.getUrl());
        datasource.setUsername(properties.getUsername());
        datasource.setConnectionProperties(properties.getConnectionProperties());
        datasource.setPassword(ConfigTools.decrypt(properties.getPublicKey(), properties.getPassword()));
        datasource.setDriverClassName(properties.getDriverClassName());
        datasource.setInitialSize(properties.getInitialSize());
        datasource.setMinIdle(properties.getMinIdle());
        datasource.setMaxActive(properties.getMaxActive());
        datasource.setMaxWait(properties.getMaxWait());
        datasource.setTimeBetweenEvictionRunsMillis(properties.getTimeBetweenEvictionRunsMillis());
        datasource.setMinEvictableIdleTimeMillis(properties.getMinEvictableIdleTimeMillis());
        datasource.setValidationQuery(properties.getValidationQuery());
        datasource.setTestWhileIdle(properties.getTestWhileIdle());
        datasource.setTestOnBorrow(properties.getTestOnBorrow());
        datasource.setTestOnReturn(properties.getTestOnReturn());
        String paramArray[] = properties.getConnectionProperties().split(";");

        try {
            datasource.setFilters(properties.getFilters());
        } catch (SQLException e) {
            log.error("========druid configuration initialization filter========", e);
        }
        return datasource;
    }
}
