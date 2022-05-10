package com.example.cookie;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Cookie" , value = "/cookie")
public class Cookie extends HttpServlet {
    protected void createCookie(HttpServletRequest request, HttpServletResponse response) throws IOException {
        javax.servlet.http.Cookie cookie = new javax.servlet.http.Cookie("key1","value1");
        response.addCookie(cookie);
        response.getWriter().write("创建成功");
    }
}
