package com.sqt.edu.account;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2019-12-17 13:40
 */
@Slf4j
@SpringBootApplication
public class Account_APP {
    public static void main(String[] args) {
        SpringApplication.run(Account_APP.class);
        log.info("==========>Account service start successful !");
    }
}
