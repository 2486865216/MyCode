package com.example.springbootweb02.Servlet;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContextListener;
import java.util.Arrays;

/**
 * proxyBeanMethods = true保证依赖的组件始终是单实例的
 */
@Configuration(proxyBeanMethods = true)
public class MyRegistConfig {

    @Bean
    public ServletRegistrationBean myServlet(){
        MyServlet myServlet = new MyServlet();
        return new ServletRegistrationBean(myServlet,"/test/myServlet");
    }
    @Bean
    public FilterRegistrationBean myFilter(){
        MyFilter myFilter = new MyFilter();
        //return new FilterRegistrationBean(myFilter, myServlet());
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(myFilter);
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/test/myServlet"));
        return filterRegistrationBean;
    }
    @Bean
    public ServletListenerRegistrationBean myServletListener(){
        MyServletContextListen myServletContextListen = new MyServletContextListen();
        return new ServletListenerRegistrationBean(myServletContextListen);
    }
}
