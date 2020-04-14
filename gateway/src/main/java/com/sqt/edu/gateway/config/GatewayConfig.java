package com.sqt.edu.gateway.config;

import com.sqt.edu.gateway.filter.AuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-01-21 16:31
 */
@Configuration
public class GatewayConfig {

    @Bean
    public AuthFilter customFilter() {
        return new AuthFilter();
    }


}
