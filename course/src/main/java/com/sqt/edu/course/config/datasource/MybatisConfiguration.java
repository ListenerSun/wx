package com.sqt.edu.course.config.datasource;

import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.toolkit.GlobalConfigUtils;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.sqt.edu.core.component.MyMetaObjectHandler;
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
import javax.sql.DataSource;


/**
 * 数据源配置
 *
 * @author ListenerSun(男, 未婚) 微信:810548252
 * @date 19-10-03
 **/
@Slf4j
@Configuration
@AutoConfigureAfter({DataSourceConfiguration.class})
@MapperScan(basePackages = {"com.sqt.edu.*.mapper*"})
public class MybatisConfiguration {

    @Bean
    public SqlSessionFactory sqlSessionFactory (@Qualifier(value = "myRoutingDataSource") MyRoutingDataSource myRoutingDataSource ) throws
            Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource( myRoutingDataSource );
        return sqlSessionFactoryBean.getObject();
    }


    @Bean(name = "mybatisSqlSessionFactoryBean")
    @Primary
    public MybatisSqlSessionFactoryBean sqlSessionFactoryBean(@Qualifier(value = "myRoutingDataSource") DataSource dataSource) throws Exception {
        log.info("==========>开始注入 MybatisSqlSessionFactoryBean");
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
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
        globalConfig.setMetaObjectHandler(new MyMetaObjectHandler());
        bean.setGlobalConfig(globalConfig);
        log.info("==========>注入 MybatisSqlSessionFactoryBean 完成!");
        return bean;
    }

    /**乐观锁插件
     * @return
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

}