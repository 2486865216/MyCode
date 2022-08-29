package com.example.Config;

import com.example.Bean.MyFactoryBeanPerson;
import com.example.Bean.Person;
import com.example.Bean.TestImport;
import com.example.Condition.LinuxCondition;
import com.example.Condition.MyImportRegistrar;
import com.example.Condition.MyImportSelector;
import com.example.Condition.WindowCondition;
import org.springframework.context.annotation.*;

/**
 * author ye
 * createDate 2022/3/14  15:37
 */
@Configuration
@Import({TestImport.class, MyImportSelector.class,  MyImportRegistrar.class})
public class MyConfig02 {

    //默认是单实例的

    /**
     * prototype:多实例的 IOC容器启动并不会去调用方法创建对象放在容器中。
     *      每次获取的时候才会调用方法创建对象；
     * singleton:单实例的（默认值）：IOC容器启动会调用方法创建对象放到IOC容器中
     *     以后每次获取就是直接从容器(map.get())中拿，
     * request:同一次请求创建一个实例
     * session:同个session创建一个实例
     * @return
     *
     * 懒加载：
     *      单实例bean:默认在容器启动的时候创建对象；
     *      懒加载：容器启动不创建对象。第一次使用（获取）Bean创建对象，并初始化
     */
    //@Scope("prototype")
    //@Lazy
    @Bean("person")
    public Person person(){
        System.out.println("向容器中添加实例...........");
        return new Person("zhangsan",18);
    }

    /**
     * @Conditional:按照一定的条件进行判断，满足条件给容器中注册bean
     */
    @Conditional({WindowCondition.class})
    @Bean("window")
    public Person person1(){
        return new Person("window",18);
    }

    @Conditional({LinuxCondition.class})
    @Bean("linux")
    public Person person2(){
        return new Person("linux",18);
    }

    /**
     * 给容器中注册组件；
     * 1)、包扫描+组件标注注解(@Controller/@Service/@Repository/@Component)[自己写的]
     * 2)、@Bean [导入的第三方包里面的组件]
     * 3)、@Import   [快速给容器中导入一个组件]
     *      1)、@Import(要导入到容器中的组件)；容器中就会自动注册这个组件，id默认是全类名
     *      2)、ImportSelector:返回需要导入的组件的全类名数组；
     *      3)、ImportBeanDefinitionRegistrar:手动注册bean到容器中
     * 4)、使用Spring提供的FactoryBean(工厂Bean)
     *      I)、默认获取到的是工厂bean调用get0 bject创建的对象
     *      2)、要获取工厂Bean本身，我们需要给id前面加一个&
     *          &factoryBeanPerson
     */
    @Bean
    public MyFactoryBeanPerson factoryBeanPerson(){
        return new MyFactoryBeanPerson();
    }
}
