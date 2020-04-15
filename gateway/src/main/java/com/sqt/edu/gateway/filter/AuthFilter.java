package com.sqt.edu.gateway.filter;

import com.sqt.edu.gateway.auth.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Description: 全局过滤器
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-01-21 16:44
 */
@Slf4j
public class AuthFilter implements GlobalFilter, Ordered {

   @Autowired
   private AuthService authService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("==========> 经过了 AuthFilter 过滤器");
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
//        if (authService.authToken(request, response)){
//            return chain.filter(exchange);
//        }


        Mono<ServerHttpResponse> just = Mono.just(response);
       return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
