package com.example.Config;

import com.example.Bean.Car;
import com.example.Bean.Cat;
import com.example.Bean.Dog;
import com.example.Bean.MyFactoryBeanPerson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * author ye
 * createDate 2022/3/15  8:32
 * bean的生命周期：
 *      bean创建---初始化----销毁的过程
 * 容器管理bean的声明周期
 *
 * 我们可以自定义初始化和销毁方法；容器在bean进行到当前生命周期的时候来调用我们自定义的初始化和销毁方法
 * 1)、指定初始化和销毁方法；
 *         指定init-method和destroy-method
 *
 *
 * 构造（对象创建）
 *      单实例：在容器启动的时候创建对象
 *      多实例：在每次获取的时候创建对象
 *
 * 初始化：
 *      对象创建完成，并赋值好，调用初始化方法。。。
 * 销毁：
 *      单实例：容器关闭的时候
 *      多实例：容器不会管理这个bean;容器不会调用销毁方法；
 *
 * 1)、指定初始化和销毁方法；
 *      通过@Bean指定init-method和destroy-method;
 * 2)、通过让Bean实现InitializingBean(定义初始化逻辑)，
 *      DisposableBean(定义销毁逻辑)
 * 3)、可以使用JSR250;
 *         @PostConstruct :在bean创建完成并且属性赋值完成；来执行初始化方法
 *         @PreDestroy :在容器销毁bean之前通知我们进行清理工作
 * 4)、BeanPostProcessor【interface】:bean的后置处理器；
 *  在bean初始化前后进行一些处理工作，
 *      postProcessBeforeInitialization:在初始化之前工作
 *      postProcessAfterInitialization:在初始化之后工作
 *
 *遍历得到容器中所有的BeanPostProcessor;挨个执行beforeInitialization,
 * 但返回null,跳出for循环，不会执行后面的BeanPostProcessor.
 *
 * BeanPostProcessor原理
 * populateBean(beanName, mbd, instanceWrapper);给bean赋值
 *  initializeBean(beanName, existingBean, null);{
 *      wrappedBean = applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName);
 *      invokeInitMethods(beanName, wrappedBean, mbd);执行自定义初始化
 *      wrappedBean = applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
 * }
 *
 * Spring底层对BeanPostProcessor的使用；
 * bean赋值，注入其他组件，@Autowired,生命周期注解功能，@Async,XXX
 */
@Configuration
@ComponentScan(value = "com.example.Bean")
public class MyConfigOfLifeCycle {

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public Car car(){
        return new Car();
    }
    @Bean
    public Cat cat(){
        return new Cat();
    }
    @Bean
    public Dog dog(){
        return new Dog();
    }
}
