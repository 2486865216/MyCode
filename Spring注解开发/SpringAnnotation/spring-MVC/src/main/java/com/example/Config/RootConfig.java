package com.example.Config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * author ye
 * createDate 2022/3/17  12:36
 */
//Spring的容器不扫描controller
@ComponentScan(value = "com.example", excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {
                Controller.class
        })
})
@Configuration
public class RootConfig {
}
