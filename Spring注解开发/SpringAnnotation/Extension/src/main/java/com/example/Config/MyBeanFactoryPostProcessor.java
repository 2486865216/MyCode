package com.example.Config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * author ye
 * createDate 2022/3/16  13:13
 */
@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("MyBeanFactoryPostProcessor");
        String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
        int count = beanDefinitionNames.length;
        System.out.println("当前BeanFactory中有"+ count +"个Bean");
        System.out.println(Arrays.asList(beanDefinitionNames));
    }
}
