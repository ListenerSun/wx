package com.sqt.edu.nacos.config;

import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.toolkit.GlobalConfigUtils;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 数据源配置
 *
 * @author ListenerSun(男, 未婚) 微信:810548252
 * @date 19-10-03
 **/
@Slf4j
@Configuration
@AutoConfigureAfter({DataSourceConfiguration.class})
@MapperScan(basePackages = {"com.sqt.edu.*.mapper*","com.sqt.edu.*.api.mapper*"})
public class MybatisConfiguration {

    @Bean
    public SqlSessionFactory sqlSessionFactory(@Qualifier(value = "dataSource") DataSource dataSource) throws
            Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "mybatisSqlSessionFactoryBean")
    @Primary
    public MybatisSqlSessionFactoryBean sqlSessionFactoryBean(@Qualifier(value = "dataSource") DataSource dataSource) throws Exception {
        log.info("==========>开始注入 MybatisSqlSessionFactoryBean");
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        Set<Resource> result = new LinkedHashSet<>(16);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            result.addAll(Arrays.asList(resolver.getResources("classpath*:mapper/*.xml")));
            result.addAll(Arrays.asList(resolver.getResources("classpath*:config/mapper/*/*.xml")));
            result.addAll(Arrays.asList(resolver.getResources("classpath*:mapper/*/*.xml")));
        } catch (IOException e) {
            log.error("获取【classpath:mapper/*/*.xml,classpath:config/mapper/*/*.xml】资源错误!异常信息:{}", e);
        }
        bean.setMapperLocations(result.toArray(new Resource[0]));
        bean.setDataSource(dataSource);
        bean.setVfs(SpringBootVFS.class);
        com.baomidou.mybatisplus.core.MybatisConfiguration configuration = new com.baomidou.mybatisplus.core.MybatisConfiguration();
        configuration.setLogImpl(StdOutImpl.class);
        configuration.setMapUnderscoreToCamelCase(true);
        //添加 乐观锁插件
        configuration.addInterceptor(optimisticLockerInterceptor());
        bean.setConfiguration(configuration);
        GlobalConfig globalConfig = GlobalConfigUtils.defaults();
        //设置 字段自动填充处理
        bean.setGlobalConfig(globalConfig);
        log.info("==========>注入 MybatisSqlSessionFactoryBean 完成!");
        return bean;
    }

    /**
     * 乐观锁插件
     *
     * @return
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }
}