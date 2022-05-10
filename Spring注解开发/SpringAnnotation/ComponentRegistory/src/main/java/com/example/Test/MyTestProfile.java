package com.example.Test;

import com.example.Config.MyConfigProfile;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * author ye
 * createDate 2022/3/15  12:00
 * 1.使用命令行参数：在虚拟机参数位置加载-Dspring.profiles,active=test
 *
 * 2、代码的方式激活某种环境：
 */
public class MyTestProfile {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    //1、创建-个applicationContext
    //2、设置需要激活的环境
    //3、注册主配置类
    //4、启动刷新容器
    @Test
    public void test() {
        ConfigurableEnvironment environment = context.getEnvironment();
        environment.setActiveProfiles("development");
        context.register(MyConfigProfile.class);
        context.refresh();
        for (String beanDefinitionName : context.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }

    }
}
