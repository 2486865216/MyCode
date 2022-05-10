package com.example.javaseservlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class HelloServlet implements Servlet {
    public HelloServlet() {
        System.out.println("constructor");
    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("serlet init");
        System.out.println("别名"+servletConfig.getServletName());
        System.out.println("初始化参数:"+servletConfig.getInitParameter("url"));
        System.out.println(servletConfig.getServletContext());
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    /*处理请求和响应*/
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
//        类型转换
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
//        获取请求的方式
        String meyhods = httpServletRequest.getMethod();
        System.out.println(meyhods);


        System.out.println("hello servlet");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("servlet is destroy");
    }
}