package com.example.javaservlet02;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello1")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("Uri:"+request.getRequestURI());
        System.out.println("Url:"+request.getRequestURL());
        System.out.println("IP:"+request.getRemoteHost());
        System.out.println("请求头："+ request.getHeader("User-Agent"));
        System.out.println("Methods:"+request.getMethod());
    }

    public void destroy() {
    }
}