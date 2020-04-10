package com.sqt.edu.course;

import com.sqt.edu.core.utils.SpringContextHelper;
import com.sqt.edu.course.component.TestImport1;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2019-12-17 13:40
 */
@EnableAsync
@Slf4j
@SpringBootApplication
@EnableFeignClients("com.sqt.edu")
@EnableDiscoveryClient
@ComponentScan(basePackages = "com.sqt.edu")
public class Account_APP {
    public static void main(String[] args) {
        SpringApplication.run(Account_APP.class);
        log.info("==========>Account service start successful !");
    }

    @Api(tags ="edu-account的testDemo")
    @RestController
    class Test{
        @ApiOperation(value = "test")
        @GetMapping("/hello")
        public String testDemo(){
            return "This is Account Service !!";
        }

    }
}
