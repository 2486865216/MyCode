package com.example.javaservlet02;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "servletIO", value = "/io")
public class servletIO extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        设置服务器字符集
        resp.setCharacterEncoding("utf-8");
//        通过响应头设置浏览器也使用utf-8字符集
        resp.setHeader("Content-Type","text/html;charset=utf-8");
//        同时设置服务器字符集,设置浏览器也使用utf-8字符集,设置响应头
//        此方法一定要在获取流之前使用
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println("你好");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
