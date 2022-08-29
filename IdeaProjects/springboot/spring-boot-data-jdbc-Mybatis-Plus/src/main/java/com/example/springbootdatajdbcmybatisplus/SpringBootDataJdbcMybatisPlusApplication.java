package com.example.springbootdatajdbcmybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.springbootdatajdbcmybatisplus.Mapper")
public class SpringBootDataJdbcMybatisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDataJdbcMybatisPlusApplication.class, args);
    }

}
