package com.sqt.edu.core.config;

import com.sqt.edu.core.filter.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-01-19 11:07
 */
@Configuration
public class FilterConfig {

    /**
     * cors过滤器
     *
     * @return
     */
    @Bean
    public CorsFilter corsFilter() {
        return new CorsFilter();
    }

}
