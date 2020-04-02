package com.sqt.edu.nacos;

import com.sqt.edu.teacher.client.TestFeginClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-04-01 16:26
 */
@Slf4j
@EnableFeignClients("com.sqt.edu")
@EnableDiscoveryClient
@ComponentScan(basePackages = "com.sqt.edu")
@SpringBootApplication
public class NacosConfigApplication {

    @RestController
    public class NacosController {

        @Autowired
        private LoadBalancerClient loadBalancerClient;
        @Autowired
        private RestTemplate restTemplate;
        @Autowired
        private TestFeginClient testFeginClient;

        @Value("${spring.application.name}")
        private String appName;

        @GetMapping("/echo/app-name")
        public String echoAppName() {
            //Access through the combination of LoadBalanceClient and RestTemolate
            ServiceInstance serviceInstance = loadBalancerClient.choose("edu-teacher");
            String path = String.format("http://%s:%s/teacher/echo/%s", serviceInstance.getHost(),
                    serviceInstance.getPort(), appName);
            System.out.println("request path:" + path);
            return restTemplate.getForObject(path, String.class);
        }

        @GetMapping("/test_fegin")
        public String testFeginClient() {
            log.error("==========> 调用teacher的 test ");

            return testFeginClient.test();
        }

    }

    //Instantiate RestTemplate Instance
    @Bean
    public RestTemplate restTemplate() {


        return new RestTemplate();
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(NacosConfigApplication.class, args);
        String userName = applicationContext.getEnvironment().getProperty("user.name");
        String userAge = applicationContext.getEnvironment().getProperty("user.age");
        System.err.println("user name :" + userName + "; age: " + userAge);
    }

}