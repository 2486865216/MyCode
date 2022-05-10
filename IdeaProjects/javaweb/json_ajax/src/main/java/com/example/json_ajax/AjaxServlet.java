package com.example.json_ajax;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import com.example.json_ajax.person.oersonjson;
import com.google.gson.Gson;
import jdk.nashorn.internal.ir.RuntimeNode;

@WebServlet(name = "AjaxServlet", value = "/ajaxServlet")
public class AjaxServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        Class c = AjaxServlet.class;
        String s = request.getParameter("action");
        try {
            Method c2 = this.getClass().getDeclaredMethod(s,HttpServletRequest.class,HttpServletResponse.class);
            c2.invoke(this,request,response);
        }  catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        Class c = AjaxServlet.class;
        String s = request.getParameter("action");
        try {
            Method c2 = this.getClass().getDeclaredMethod(s,HttpServletRequest.class,HttpServletResponse.class);
            c2.invoke(this,request,response);
        }  catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    protected void javaScriptAjax(HttpServletRequest request,HttpServletResponse response) throws IOException {
        System.out.println("AJAX请求以发送");
        oersonjson o = new oersonjson(1,"hello ajax");
        Gson gson = new Gson();
        String s = gson.toJson(o);
        response.getWriter().write(s);
    }
    protected void jqueryAjax(HttpServletRequest request,HttpServletResponse response) throws IOException {
        System.out.println("jquery AJAX请求以发送");
        oersonjson o = new oersonjson(1,"hello jquery ajax");
        Gson gson = new Gson();
        String s = gson.toJson(o);
        response.getWriter().write(s);
    }
    protected void ajaxSerialize (HttpServletRequest request,HttpServletResponse response) throws IOException {
        System.out.println("jquery AJAX请求以发送");
        System.out.println("user:"+request.getParameter("user"));
        System.out.println("password:"+request.getParameter("password"));
        oersonjson o = new oersonjson(1,"hello jquery ajax");
        Gson gson = new Gson();
        String s = gson.toJson(o);
        response.getWriter().write(s);
    }

}
