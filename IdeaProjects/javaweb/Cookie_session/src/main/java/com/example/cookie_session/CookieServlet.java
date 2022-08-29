package com.example.cookie_session;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@WebServlet(name = "CookieServlet", value = "/CookieServlet")
public class CookieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        Class c = CookieServlet.class;
        try {
            Object cookieServlet =c.newInstance();
            Method test = c.getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);

            test.invoke(cookieServlet,request,response);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
//        createCookie(request,response);
//        getCookie(request,response);
//        updateCookie(request,response);
//        response.setCharacterEncoding("utf-8");
        //response.sendRedirect("http://localhost:8080/Cookie_session_war_exploded/cookie.html");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    protected void createCookie(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cookie cookie = new Cookie("key1","value1");
        response.setCharacterEncoding("utf-8");
//        cookie.setMaxAge(30);
        /*
        * 正数表示存活时间
        * 负数表示浏览器关闭是删除
        * 0表示立马删除*/
        response.addCookie(cookie);
        Cookie cookie1 = new Cookie("user","value2");
        response.addCookie(cookie1);
        response.getWriter().write("createCookie");
        System.out.println("createCookie");
    }
    protected void getCookie(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cookie[] cookie = request.getCookies();
        response.setCharacterEncoding("utf-8");
        for (Cookie cookie1:cookie){
            response.getWriter().write("Cookie:"+cookie1.getName()+"   Value:"+cookie1.getValue());
        }
        response.getWriter().write("getCookie");
        System.out.println("getCookie");
    }
    protected void updateCookie(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cookie[] cookie = request.getCookies();
        response.setCharacterEncoding("utf-8");
//        Cookie cookie1 = new Cookie("key1","value1");
//        response.addCookie(cookie1);
        for (Cookie cookie1:cookie){
            if(cookie1.getName().equals("key2")){
                cookie1.setValue("hello");
                response.addCookie(cookie1);
            }
        }
        System.out.println("updateCookie");
    }
}
