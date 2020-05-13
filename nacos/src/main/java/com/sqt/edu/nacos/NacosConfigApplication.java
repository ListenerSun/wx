package com.sqt.edu.nacos;

import com.alibaba.cloud.sentinel.SentinelProperties;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.sqt.edu.common.base.JsonResult;
import com.sqt.edu.nacos.service.TestService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

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
        private TestService testService;
//        @Autowired
//        private TestFeginClient testFeginClient;

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

//        @GetMapping("/test_fegin")
//        public JsonResult testFeginClient() {
//            log.error("==========> 调用teacher的 test ");
//            return testFeginClient.test();
//        }

        @SentinelResource(value = "test_annotation_01", fallback = "helloFallback")
        @GetMapping("/test_annotation")
        public JsonResult test_annotation() throws InterruptedException {
            log.error("========进来了");
            TestAnnotationService ts = new TestAnnotationService();
            return ts.test_annotation();
        }

        // Fallback 函数，函数签名与原函数一致或加一个 Throwable 类型的参数.
        public JsonResult helloFallback() {
            log.error("====== 调用了 fallback");
            return new JsonResult("调用了 fallback");
        }

        // Block 异常处理函数，参数最后多一个 BlockException，其余与原函数一致.
        public JsonResult exceptionHandler(BlockException ex) {
            // Do some log here.
            log.info("调用了 exceptionHandler");
            return new JsonResult(ex);
        }

        @ApiOperation(value = "test_annotation_01tx事务测试")
        @GetMapping("/tx")
        public JsonResult createTx(){
            return testService.createTx();
        }

    }

    //Instantiate RestTemplate Instance
    @Bean
    public RestTemplate restTemplate() {

        return new RestTemplate();
    }

    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(NacosConfigApplication.class, args);
            String userName = applicationContext.getEnvironment().getProperty("user.name");
            String userAge = applicationContext.getEnvironment().getProperty("user.age");
            String enable = applicationContext.getEnvironment().getProperty("swagger.enable");
            System.err.println("user name :" + userName + "; age: " + userAge);
            System.err.println("enable :" + enable);
            TimeUnit.SECONDS.sleep(1);


    }

}