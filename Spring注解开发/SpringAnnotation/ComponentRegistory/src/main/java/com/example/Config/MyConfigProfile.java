package com.example.Config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

/**
 * author ye
 * createDate 2022/3/15  11:50
 * Profile:
 *
 * Spring为我们提供的可以根据当前环境，动态的激活和切换一系列组件的功能：
 * 开发环境、测试环境、生产环境；
 * 数据源：(/A)(/B)(/C);
 *
 * @Prof11e: 指定组件在哪个环境的情况下才能被注册到容器中，不指定，任何环境下都能注册这个组件
 * I)、加了环境标识的bean,只有这个环境被激活的时候才能注册到容器中。默认是default环境
 * 2)、写在配置类上，只有是指定的环境的时候，整个配置类里面的所有配置才能开始生效
 * 3)、设有标注环境标识的bean在，任何环境下都是加载的：
 *
 * 1.使用命令行参数：在虚拟机参数位置加载-Dspring.profiles,active=test
 */
@Configuration
public class MyConfigProfile {
    @Profile("default")
    @Bean("test")
    public DataSource dataSourceTest() throws Exception{
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser("root");
        dataSource.setPassword("123456");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        return dataSource;
    }

    @Profile("development")
    @Bean("development")
    public DataSource dataSourceDevelopment() throws Exception{
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser("root");
        dataSource.setPassword("123456");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/Docker");
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        return dataSource;
    }
}
