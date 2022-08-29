package com.example.javaservlet02;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Arrays;

@WebServlet(name = "HelloServlet02", value = "/hello2")
public class HelloServlet02 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        String user = request.getParameter("user");
        String password = request.getParameter("password");
        String[] aihao = request.getParameterValues("aihao");
        System.out.println("user:"+user);
        System.out.println("password:"+password);
        System.out.println("aihao:"+ Arrays.asList(aihao));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
