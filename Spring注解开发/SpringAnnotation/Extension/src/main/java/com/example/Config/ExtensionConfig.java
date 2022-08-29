package com.example.Config;

import com.example.Bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * author ye
 * createDate 2022/3/16  13:07
 * 扩展原理：
 * BeanPostProcessor:           bean后置处理器，bean创建对象初始化前后进行拦截工作的
 * BeanFactoryPostProcessor:    beanFactory的后置处理器； 在BeanFactory标准初始化之后调用 所有的bean定义已经保存加载到beanFactory,但是bean的实例还未创建
 * 1、BeanFactoryPostProcessor:beanFactory的后置处理器；
 *      在BeanFactory标准初始化之后调用，来定制和修改BeanFactory的内容；
 *      所有的bean定义已经保存加载到beanFactory,但是bean的实例还未创建
 *
 * BeanFactoryPostProcessor原理：
 * 1)、IOC容器创建对象
 * 2).invokeBeanFactoryPostProcessors(beanFactory);BeanFactoryPostProcessor;
 *      如何找到所有的BeanFactoryPostProcessor并执行他们的方法：
 *          1)、直接在BeanFactory中找到所有类型是BeanFactoryPostProcessor的组件，并执行他们的方法
 *          2)、在初始化创建其他组件前面执行
 *
 * 2,BeanDefinitionRegistryPostProcessor extends BeanFactoryPostProcessor
 *      postProcessBeanDefinitionRegistry();
 *      在所有bean定义信息将要被加载，bean实例还未创建的
 *      //BeanDefinitionRegistry Bean定义信息的保存中心，以后BeanFactory就是按BeanDefinitionRegistry里面保存的每一个bean定义信息创建bean实例：
 *
 *      优先于BeanFactoryPostProcessor执行：
 *      利用BeanDefinitionRegistryPostProcessor给容器中再额外添加一些组件；
 *
 * 原理
 *      1)、IOC创建对象
 *      2)refresh()->invokeBeanFactoryPostProcessors(beanFactory);
 *      3)、从容器中获取到所有的BeanDefinitionRegistryPostProcessor组件。
 *          1、依次触发所有的postProcessBeanDefinitionRegistry()
 *          2、再来触发postProcessBeanFactory()方法BeanFactoryPostProcessor,
 *      4)、再来从容器中找到BeanFactoryPostProcessor组件；然后依次触发postProcessBeanFactory()方法
 *
 * 3、ApplicationListener 监听容器中发布的事件。事件驱动模型开发；
 *      public interface ApplicationListener<E extends ApplicationEvent
 *          监听ApplicationEvent及其下面的子事件；
 *      步骤：
 *          1)、写一个监听器来监听某个事件(ApplicationEvent及其子类)
 *              @EventListener
 *              原理：使用EventListenerMethodProcessor处理器来解析方法上的@EventListener;
 *
 *          2)、把监听器加入到容器；
 *          3)、只要容器中有相关事件的发布，我们就能监听到这个事件
 *              ContextRefreshedEvent:容器刷新完成（所有bean都完全创建）会发布这个事件：
 *              ContextClosedEvent:关闭容器会发布这个事件；
 *          4)、发布一个事件：
 *              applicationContext.publishEvent();
 *      原理:
 *          ContextRefreshedEvent、IOCTest Ext$l[source=我发布的时i间]、ContextClosedEvent;
 *              1)、ContextRefreshedEvent事件：
 *                  1)、容器创建对象：refresh();
 *                  2)、finishRefresh();容器刷新完成会发布ContextRefreshedEvent事件
 *              2)、自己发布事件
 *                  事件发布流程：
 *                  3).publishEvent(new ContextRefreshedEvent(this));
 *                          1)、获取事件的多播器（派发器）：getApplicationEventMulticaster()
 *                          2)、multicastEvent派发事件：
 *                          3)、获取到所有的ApplicationListener;
 *                              for (final ApplicationListener<?>listener getApplicationListeners(event,type)
 *                                  1)、如果有Executor,可以支持使用Executor:进行异步派发；
 *                                      Executor executor getTaskExecutor();
 *                                  2)、否则，同步的方式直接执行listener方法；invokeListene时(listener)event);
 *                                      拿到listener回调onApplicationEvent方法：
 *              【事件多播器（派发器）】
 *                  1)、容器创建对象：refresh();
 *                  2)、initApplicationEventMulticaster();初始化ApplicationEventMulticaster;
 *                      I)、先去容器中找有设有id=“applicationEventMulticaster的组件；
 *                      2)、如果设有this.applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
 *                          并且加入到容器中，我们就可以在其他组件要派发事件，自动注入这个applicationEventMulticaster
 *              【容器中有哪些监听器】
 *                  1)、容器创建对象：refresh();
 *                  2).registerListeners();
 *                      从容器中掌到所有的监听器，把他们注册到applicationEventMulticaster中；
 *                      //将listener注册到ApplicationEventMulticaster中
 *                      String[] listenerBeanNames = getBeanNamesForType(ApplicationListener.class, true, false);
 *          SmartInitializingsingleton:->aftersingletonsInstantiated();
 *              1)、ioc容器创建对象并refresh();
 *              2)、finishBeanFactoryInitialization(beanFactory);初始化剩下的单实例bean;
 *                  1)、先创建所有的单实例bean;getBean();
 *                  2)、获取所有创建好的单实例bean,判断是否是SmartInitializingsingleton类型的；
 *                      如果是就调用aftersingletonsInstantiated();
 */
@Configuration
@ComponentScan("com.example.Config")
public class ExtensionConfig {
    @Bean
    public Person person(){
        return new Person("hello", 18);
    }
}
