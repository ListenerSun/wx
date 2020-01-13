package com.sqt.edu.course;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2019-12-17 13:40
 */
@EnableAsync
@Slf4j
@SpringBootApplication
@EnableFeignClients("com.sqt.edu")
@EnableEurekaClient
@ComponentScan(basePackages = "com.sqt.edu")
public class Account_APP {
    public static void main(String[] args) {
        SpringApplication.run(Account_APP.class);
        log.info("==========>Account service start successful !");
    }
}
