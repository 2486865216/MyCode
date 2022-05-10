package com.qiqi.boot;

import com.qiqi.boot.bean.Pet;
import com.qiqi.boot.bean.User;
import com.qiqi.boot.config.Myconfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 主程序类
 * @SpringBootApplication这是一个SpringBoot应用
 *
 * 主程序所在包及其子包默认扫描
 *
 * 改变包扫描路径
 * @SpringBootApplication(scanBasePackages = "com.qiqi")
 *
 * @SpringBootApplication
 * 等同于
 * @SpringBootConfiguration
 * @EnableAutoConfiguration
 * @ComponentScan
 */
@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(MainApplication.class, args);

        /*//查看容器的组件
        String[] names = run.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }

        //从容器中获取组件
        Pet pet1 = run.getBean("tom",Pet.class);
        Pet pet2 = run.getBean("tom",Pet.class);
        System.out.println(pet1==pet2);

        //com.qiqi.boot.config.Myconfig$$EnhancerBySpringCGLIB$$24e432e7@213deac2
        Myconfig bean = run.getBean(Myconfig.class);
        System.out.println(bean);

        //如果@Configuration(proxyBeanMethods = true)代理对象调用方法。
        //SpringBoot总会检查这个组件是否在容器中
        //保持组件单实例
        User user1 = bean.user01();
        User user2 = bean.user01();
        System.out.println(user1==user2);

        User user01 = run.getBean("user01",User.class);
        Pet tom = run.getBean("tom",Pet.class);

        System.out.println("User Pet:"+(user01.getPet()==tom));

        String[] beanNamesForType = run.getBeanNamesForType(User.class);
        for (String s : beanNamesForType) {
            System.out.println(s);
        }*/

        //boolean tom = run.containsBean("tom");
        //System.out.println("容器中有tom组件:"+tom);
        //
        //boolean hello1 = run.containsBean("hello1");
        //boolean hello2 = run.containsBean("hello2");
        //
        //System.out.println("hello1:"+hello1+",hello2"+hello2);
    }
}
