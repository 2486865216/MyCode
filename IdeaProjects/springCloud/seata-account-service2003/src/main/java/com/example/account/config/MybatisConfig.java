package com.example.account.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * author ye
 * createDate 2022/8/1  20:26
 */
@Configuration
@MapperScan({"com.example.account.dao"})
public class MybatisConfig {
}
