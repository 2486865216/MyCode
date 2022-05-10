package com.example.javaservlet02;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "response1" ,value = "/response1")
public class response1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("rsponse1");
//        resp.setStatus(302);
//        resp.setHeader("location","http://localhost:8080/javaservlet02_war_exploded/response2");
        resp.sendRedirect("http://localhost:8080/javaservlet02_war_exploded/response2");
    }
}
