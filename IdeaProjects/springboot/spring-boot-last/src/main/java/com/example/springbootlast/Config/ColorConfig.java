package com.example.springbootlast.Config;

import com.example.springbootlast.Bean.Color;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
public class ColorConfig {
    @Profile("prod")
    @Bean
    public Color red(){
        return new Color("red");
    }

    @Profile("test")
    @Bean
    public Color green(){
        return new Color("green");
    }
}
