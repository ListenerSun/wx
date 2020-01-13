package com.sqt.edu.course.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * 数据源配置
 *
 * @author lfy
 * @date 19-7-1
 **/
@Configuration
public class DataSourceConfiguration {

    /**
     * 数据源类型
     */
    @Value("${druid.type}")
    private Class<? extends DataSource> dataSourceType;

    /**
     * 默认是数据源
     */
    @Value("${druid.defaultDs}")
    private String defaultDs;


    @Bean
    @Primary
    @ConfigurationProperties(prefix = "druid.master")
    public DataSource dataSource() {
        DruidDataSource druidDataSource = (DruidDataSource) DataSourceBuilder.create().type(dataSourceType).build();
//        DbContextHolder.addDataSource("master", druidDataSource);
//        DbContextHolder.setDefaultDs(defaultDs);
        return druidDataSource;
    }

//    @Bean(name = "slaveDataSource")
//    @ConfigurationProperties(prefix = "druid.slave")
//    public DataSource slaveDataSource() {
//        DruidDataSource druidDataSource = (DruidDataSource) DataSourceBuilder.create().type(dataSourceType).build();
//        DbContextHolder.addDataSource("slave", druidDataSource);
//        DbContextHolder.setDefaultDs(defaultDs);
//        return druidDataSource;
//    }
}