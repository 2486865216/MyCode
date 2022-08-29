package com.example.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * author ye
 * createDate 2022/3/17  12:07
 */
public class UserListener implements ServletContextListener {
    //监听ServletContext启动初始化
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("UserListener Initialized");
    }

    //监听ServletContext停止
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("UserListener Destroyed");
    }
}
