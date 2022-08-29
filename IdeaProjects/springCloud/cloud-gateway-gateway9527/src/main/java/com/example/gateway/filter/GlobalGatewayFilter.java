package com.example.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * author ye
 * createDate 2022/7/14  20:21
 */
@Component
@Slf4j
public class GlobalGatewayFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("===========come in GlobalGatewayFilter================");
        String username = exchange.getRequest().getQueryParams().getFirst("username");
        if (username == null){
            log.info("username为null,非法用户");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        }
        return  chain.filter(exchange);
    }

    //加载过滤器的顺序
    @Override
    public int getOrder() {
        return 0;
    }
}
