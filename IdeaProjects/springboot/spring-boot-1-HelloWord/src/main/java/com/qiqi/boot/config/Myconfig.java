package com.qiqi.boot.config;

import com.qiqi.boot.bean.Car;
import com.qiqi.boot.bean.Pet;
import com.qiqi.boot.bean.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * 1.配置类里面使用@Bean标注在方法上给容器注册组件，默认是单实例
 * 2.配置类本身也是组件
 * 3.proxyBeanMethods:代理Bean方法
 *      Full(proxyBeanMethods = true)
 *      Lite(proxyBeanMethods = false)
 *      组件依赖
 *
 *      配置类组件之间无依赖关系用Lite模式加速容器启动过程，减少判断
 *      配置类组件之间有依赖关系，方法会被调用得到之前的单实列组件，用Full模式
 *
 * 4.@Import({User.class,Pet.class})
 *      给容器中自动创建出这两个类型的组件
 *      默认组件的名字就是全类名
 *          com.qiqi.boot.bean.User
 */

@ConditionalOnMissingBean(name = "tom")
@Import({User.class,Pet.class})
@Configuration(proxyBeanMethods = true)
@ImportResource("classpath:beans.xml")
@EnableConfigurationProperties(Car.class)
//1.开启CAr配置绑定功能
//2.把这个CAr这个组件自动注册到容器中
public class Myconfig {

    //外部无论对配置类中的这个组件注册方法调用多少次获取的都是之前注册容器中的单实例对象
    @Bean
    public User user01(){
        User user = new User("word",20);
        //User组件依赖了Pet组件
        user.setPet(pet01());
        return user;
    }
    @Bean("tom")
    public Pet pet01(){
        Pet tomcat = new Pet("Tomcat");
        return  tomcat;
    }
}
