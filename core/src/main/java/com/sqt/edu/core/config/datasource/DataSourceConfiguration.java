package com.sqt.edu.core.config.datasource;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.sqt.edu.common.constant.CommonEnum;
import com.sqt.edu.core.holder.DbContextHolder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据源配置
 *
 * @author ListenerSun(男, 未婚) 微信:810548252
 * @date 19-12-20
 **/
@Configuration
public class DataSourceConfiguration {


    /**
     * 默认是数据源
     */
    @Value("${spring.datasource.druid.defaultDs}")
    private String defaultDs;

    @Bean(name = "dataSourceMaster")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.druid.master")
    public DataSource dataSourceMaster() {
        DataSource druidDataSource = DruidDataSourceBuilder.create().build();
        DbContextHolder.addDataSource(CommonEnum.DsType.DS_MASTER.getValue(), druidDataSource);

        return druidDataSource;
    }

    @Bean(name = "dataSourceSlave")
    @ConfigurationProperties(prefix = "spring.datasource.druid.slave")
    public DataSource dataSourceSlave() {
        DataSource druidDataSource = DruidDataSourceBuilder.create().build();
        DbContextHolder.addDataSource(CommonEnum.DsType.DS_SLAVE.getValue(), druidDataSource);
        return druidDataSource;
    }

    @Bean(name = "myRoutingDataSource")
    public MyRoutingDataSource dataSource(@Qualifier("dataSourceMaster") DataSource dataSourceMaster,
                                          @Qualifier("dataSourceSlave") DataSource dataSourceSlave) {
        MyRoutingDataSource dynamicDataSource = new MyRoutingDataSource();
        Map<Object, Object> targetDataResources = new HashMap<>();
        targetDataResources.put(CommonEnum.DsType.DS_MASTER.getValue(), dataSourceMaster);
        targetDataResources.put(CommonEnum.DsType.DS_SLAVE.getValue(), dataSourceSlave);
        //设置默认数据源
        dynamicDataSource.setDefaultTargetDataSource(dataSourceMaster);
        dynamicDataSource.setTargetDataSources(targetDataResources);
        DbContextHolder.setDefaultDs(defaultDs);
        return dynamicDataSource;
    }

}