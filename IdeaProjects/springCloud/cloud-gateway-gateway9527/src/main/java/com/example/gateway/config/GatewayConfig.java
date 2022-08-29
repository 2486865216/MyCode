package com.example.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * author ye
 * createDate 2022/7/14  19:47
 */
@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator consumerRoute(RouteLocatorBuilder routeLocatorBuilder){
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        routes.route("test",
                r -> r.path("/guonei")
                .uri("http://news.baidu.com/guonei"))
                .build();
        return routes.build();
    }
}
