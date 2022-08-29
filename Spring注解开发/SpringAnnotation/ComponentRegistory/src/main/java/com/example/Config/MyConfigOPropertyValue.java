package com.example.Config;

import com.example.Bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * author ye
 * createDate 2022/3/15  9:24
 */
@Configuration
//使用@PropertySource读取外部配置文件中的k/v保存到运行的环境变量中
@PropertySource(value = {"classpath:/person.properties"})
public class MyConfigOPropertyValue {
    @Bean
    public Person person(){
        return new Person();
    }
}
