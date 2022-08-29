package com.example.Config;

import com.example.Bean.Person;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.stereotype.Component;

/**
 * author ye
 * createDate 2022/3/16  13:27
 */
@Component
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
    //BeanDefinitionRegistry Bean定义信息的保存中心，以后BeanFactory就是按BeanDefinitionRegistry里面保存的每一个bean定义信息创建bean实例：
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        System.out.println("postProcessBeanDefinitionRegistry ... Bean的数量:" + registry.getBeanDefinitionNames().length);
        Class beanClass;
        RootBeanDefinition person = new RootBeanDefinition(Person.class);
        registry.registerBeanDefinition("hello", person);
    }
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("postProcessBeanFactory ... Bean的数量:" + beanFactory.getBeanDefinitionNames().length);
    }
}
