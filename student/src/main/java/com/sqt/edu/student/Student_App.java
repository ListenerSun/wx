package com.sqt.edu.student;

import com.sqt.edu.common.base.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-02-12 14:23
 */
@EnableAsync
@Slf4j
@SpringBootApplication
@EnableFeignClients("com.sqt.edu")
@EnableDiscoveryClient
@ComponentScan(basePackages = "com.sqt.edu")
public class Student_App {
    public static void main(String[] args) {
        SpringApplication.run(Student_App.class);
        log.info("===========>Service Student Start Successful !");
    }

    @RestController
    class TestController{

        @GetMapping("/hello")
        public JsonResult hello(){
            return new JsonResult("Hello! This is Student Service！");
        }
    }
}
