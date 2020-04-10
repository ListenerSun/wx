package com.sqt.edu.course;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import com.sqt.edu.common.base.JsonResult;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JndiDataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-01-09 11:37
 */
@Slf4j
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,DruidDataSourceAutoConfigure.class})
@EnableFeignClients("com.sqt.edu")
@EnableDiscoveryClient
@ComponentScan(basePackages = "com.sqt.edu")
public class Course_App {
    public static void main(String[] args) {
        SpringApplication.run(Course_App.class);
        log.info("==========> Course Service Start Successful !");
    }

    @RestController
    class TestController{

        @GetMapping("/hello")
        public JsonResult hello(){
            return new JsonResult("Hello! This is Course Service!");
        }
    }
}
