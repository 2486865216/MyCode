package com.example.Test;

import com.example.Bean.Person;
import com.example.Config.MyConfig;
import com.example.Config.MyConfig02;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
/**
 * author ye
 * createDate 2022/3/14  14:59
 */
public class MyTest {
    @Test
    public void test() {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        Person person = (Person) context.getBean("person");
        System.out.println(person);
    }

    @Test
    public void test1() {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        for (String beanDefinitionName : context.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }

    }

    @Test
    public void test2() {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig02.class);
        //Person person = (Person) context.getBean("person");
        //System.out.println(person);
    }

    @Test
    public void test3() {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig02.class);
        //获取操作系统名
        //Environment environment = context.getEnvironment();
        //String property = environment.getProperty("os.name");
        //System.out.println(property);

        for (String beanDefinitionName : context.getBeanNamesForType(Person.class)) {
            System.out.println(beanDefinitionName);
        }
    }

    @Test
    public void tes4t() {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig02.class);
        for (String beanDefinitionName : context.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }

    }

    @Test
    public void test5() {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig02.class);
        Object factoryBeanPerson = context.getBean("factoryBeanPerson");
        System.out.println(factoryBeanPerson.getClass());
        System.out.println(factoryBeanPerson.getClass() == factoryBeanPerson.getClass());
        System.out.println(context.getBean("&factoryBeanPerson").getClass());
    }
}
