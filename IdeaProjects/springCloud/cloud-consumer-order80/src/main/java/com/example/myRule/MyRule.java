package com.example.myRule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * author ye
 * createDate 2022/5/25  14:05
 */
@Configuration
public class MyRule {
    @Bean
    public IRule myRule(){
        return new RandomRule();
    }
}
