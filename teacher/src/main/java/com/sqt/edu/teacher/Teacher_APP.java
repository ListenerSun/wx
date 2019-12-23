package com.sqt.edu.teacher;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2019-11-27 17:02
 */
@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
public class Teacher_APP {
    public static void main(String[] args) {
        SpringApplication.run(Teacher_APP.class);
        log.info("==========>Teacher 服务启动成功!");
        Math.max(10,1);
    }
}
