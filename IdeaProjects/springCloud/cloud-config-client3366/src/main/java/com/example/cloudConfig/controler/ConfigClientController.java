package com.example.cloudConfig.controler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * author ye
 * createDate 2022/7/18  19:57
 */
@RestController
@RefreshScope
public class ConfigClientController {
    @Value("${config.info}")
    private String info;

    @GetMapping("/configInfo")
    public String getInfo(){
        return info;
    }
}
