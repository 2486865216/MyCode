package com.example.allibabaConfig.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * author ye
 * createDate 2022/7/22  20:14
 */
@RestController
@RefreshScope //支持动态刷新
public class ConfigController {
    @Value("${config.info}")
    private String info;

    @GetMapping("/info")
    public String getInfo(){
        return info;
    }
}
