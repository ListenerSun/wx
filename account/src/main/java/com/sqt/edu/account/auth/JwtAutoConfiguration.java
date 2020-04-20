package com.sqt.edu.account.auth;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-01-21 15:05
 */
@Configuration
@Slf4j
public class JwtAutoConfiguration {

    @Value("${jwt.secret:listenersun}")
    String secret;

    @Value("${jwt.expiration:36000}")
    Long expiration;

    /**
     * jwt工具类
     */
    @Bean
    public JwtTokenUtil jwtTokenUtil() {
        if (StringUtils.isBlank(secret)) {
            secret = "listenersun";
        }
        if (expiration == null || expiration == 0L) {
            expiration = 36000L;
        }
        log.info("JwtAutoConfiguration parmater={}", toString());
        return new JwtTokenUtil(secret, expiration);
    }

    @Override
    public String toString() {
        return "JwtAutoConfiguration{" +
                "secret='" + secret + '\'' +
                ", expiration='" + expiration + '\'' +
                '}';
    }
}

