package com.sqt.edu.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-01-20 17:45
 */
@Slf4j
@ComponentScan(basePackages = "com.sqt.edu")
@EnableDiscoveryClient
@SpringBootApplication
public class Gateway_App {

    public static void main(String[] args) {
        SpringApplication.run(Gateway_App.class);
        log.info("==========>Gateway-Service Start Successful !");
    }

    @RestController
    class GatewayTest {
        @RequestMapping("/test_demo")
        public String testDemo() {
            return "This is Gateway-Service ！！";
        }
    }

}
