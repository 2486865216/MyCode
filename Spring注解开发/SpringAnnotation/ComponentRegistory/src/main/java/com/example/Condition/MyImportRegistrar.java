package com.example.Condition;

import com.example.Bean.TestImport003;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * author ye
 * createDate 2022/3/14  16:19
 */
public class MyImportRegistrar implements ImportBeanDefinitionRegistrar {
    /**
     * AnnotationMetadata:当前类的注解信息
     * BeanDefinitionRegistry:BeanDefinition注册类；
     *      把所有需要添加到容器中的bean;调用
     *      BeanDefinitionRegistry.registerBeanDefinition手工注册进来
     */
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        boolean testImport001 = registry.containsBeanDefinition("com.example.Bean.TestImport001");
        boolean testImport002 = registry.containsBeanDefinition("com.example.Bean.TestImport002");

        if (testImport001 && testImport002){
            //指定Bean定义信息；(Bean的类型，Bean,。。)
            RootBeanDefinition testImport003 = new RootBeanDefinition(TestImport003.class);
            //注册一个Bean,指定bean名
            registry.registerBeanDefinition("003", testImport003);
        }
    }
}
