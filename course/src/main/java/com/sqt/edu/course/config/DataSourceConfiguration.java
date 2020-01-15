package com.sqt.edu.course.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

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


    @Bean(name = "dataSourceMaster")
    @Primary
    @ConfigurationProperties(prefix = "druid.master")
    public DataSource dataSource() {
        DruidDataSource druidDataSource = DataSourceBuilder.create().type(DruidDataSource.class).build();
        DbContextHolder.addDataSource("master", druidDataSource);
        DbContextHolder.setDefaultDs(defaultDs);
        return druidDataSource;
    }

    @Bean(name = "dataSourceSlave")
    @ConfigurationProperties(prefix = "druid.slave")
    public DataSource slaveDataSource() {
        DruidDataSource druidDataSource = DataSourceBuilder.create().type(DruidDataSource.class).build();
        DbContextHolder.addDataSource("slave", druidDataSource);
        DbContextHolder.setDefaultDs(defaultDs);
        return druidDataSource;
    }

    @Bean(name = "myRoutingDataSource")
    public MyRoutingDataSource dataSource(@Qualifier("dataSourceMaster") DataSource dataSourceMaster,
                                          @Qualifier("dataSourceSlave") DataSource dataSourceSlave) {
        MyRoutingDataSource dynamicDataSource = new MyRoutingDataSource();
        Map<Object, Object> targetDataResources = new HashMap<>();
        for (Map.Entry<String, DataSource> entry : DbContextHolder.getDataSources().entrySet()) {
            targetDataResources.put(entry.getKey(), entry.getValue());
        }
        //设置默认数据源
        dynamicDataSource.setDefaultTargetDataSource(DbContextHolder.getDefaultDataSource());
        dynamicDataSource.setTargetDataSources(targetDataResources);
        return dynamicDataSource;
    }

}