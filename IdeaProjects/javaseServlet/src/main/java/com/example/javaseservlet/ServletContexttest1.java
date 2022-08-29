package com.example.javaseservlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

//@WebServlet(name = "ServletContext", value = "/ServletContext")
public class ServletContexttest1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = getServletContext();
        String user = context.getInitParameter("user");
        System.out.println("user::::"+user);
        System.out.println("工程路径："+context.getContextPath());
        System.out.println(context.getRealPath("a.html"));
        System.out.println("获取到的是："+context.getAttribute("key1"));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
