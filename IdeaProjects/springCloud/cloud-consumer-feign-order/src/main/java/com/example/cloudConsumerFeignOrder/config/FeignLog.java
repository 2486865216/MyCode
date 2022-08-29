package com.example.cloudConsumerFeignOrder.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * author ye
 * createDate 2022/7/8  13:56
 */
@Configuration
public class FeignLog {
        @Bean
        Logger.Level feignLoggerLevel() {
            return Logger.Level.FULL;
        }
}
