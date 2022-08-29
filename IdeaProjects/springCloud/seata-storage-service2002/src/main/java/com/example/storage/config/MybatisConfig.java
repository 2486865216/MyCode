package com.example.storage.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * author ye
 * createDate 2022/8/1  20:26
 */
@Configuration
@MapperScan({"com.example.storage.dao"})
public class MybatisConfig {
}
