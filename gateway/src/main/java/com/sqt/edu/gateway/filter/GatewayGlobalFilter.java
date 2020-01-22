package com.sqt.edu.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Description: 全局过滤器
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-01-21 16:44
 */
@Slf4j
public class GatewayGlobalFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        log.info("==========> 全局过滤器 GatewayGlobalFilter 执行了!");
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
