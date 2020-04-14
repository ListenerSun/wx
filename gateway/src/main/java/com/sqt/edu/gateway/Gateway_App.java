package com.sqt.edu.gateway;

import com.sqt.edu.core.exception.GlobalExceptionTranslator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-01-20 17:45
 *
 * 排除扫描 GlobalExceptionTranslator 类， gateway使用的webflux,去除与spring web相关
 */
@Slf4j
@ComponentScan(basePackages = "com.sqt.edu",
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes ={GlobalExceptionTranslator.class})})
@EnableDiscoveryClient
@SpringBootApplication()
public class Gateway_App {

    public static void main(String[] args) {
        SpringApplication.run(Gateway_App.class);
        log.info("==========>Gateway-Service Start Successful !");
    }

    @RestController
    class GatewayTest {

        @Autowired
        private DiscoveryClient discoveryClient;

        @RequestMapping("/test_demo")
        public String testDemo() {

            return "This is Gateway-Service ！！";
        }
    }

}
