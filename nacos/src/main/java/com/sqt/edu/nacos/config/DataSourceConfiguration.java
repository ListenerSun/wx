package com.sqt.edu.nacos.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.sqt.edu.common.constant.CommonEnum;
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


    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    public DataSource dataSource() {
        DataSource druidDataSource = DruidDataSourceBuilder.create().build();
        return druidDataSource;
    }

}