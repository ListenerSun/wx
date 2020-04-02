package com.sqt.edu.teacher;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2019-11-27 17:02
 */
@EnableAsync
@Slf4j
@SpringBootApplication
@EnableFeignClients("com.sqt.edu")
@EnableDiscoveryClient
@ComponentScan(basePackages = "com.sqt.edu")
public class Teacher_APP {
    public static void main(String[] args) {
        SpringApplication.run(Teacher_APP.class);
        log.info("==========>Teacher Service Start Successful!");
        Math.max(10,1);
    }

    @RestController
    public class EchoController {
        @GetMapping(value = "/echo/{string}")
        public String echo(@PathVariable String string) {
            return "Hello Nacos Discovery " + string;
        }
    }
}
