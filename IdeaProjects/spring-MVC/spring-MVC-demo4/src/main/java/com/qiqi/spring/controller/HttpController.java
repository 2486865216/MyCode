package com.qiqi.spring.controller;

import com.qiqi.spring.bean.User;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/*
* @RestController注解是springMVC提供的一个复合注解，标识在控制器的类上，就相当于为类添加了
* @controller注解，并且为其中的每个方法添加了@ResponseBody注解
* */


@Controller
public class HttpController {
    @RequestMapping("/testRequestBody")
    public String testRequestBody(@RequestBody String requestBody){
        System.out.println("requestBody: "+requestBody);
        return "success";
    }

    @RequestMapping("/testRequestEntity")
    public String testRequestEntity(RequestEntity<String> requestEntity){
        System.out.println("requestHeaders: "+requestEntity.getHeaders());
        System.out.println("requestBody: "+requestEntity.getBody());
        return "success";
    }

    @RequestMapping("/testResponse")
    public void testResponse(HttpServletResponse response) throws IOException {
        response.getWriter().write("hello,response");
    }

    @RequestMapping("/testResponseBody")
    @ResponseBody
    public String testResponseBody(){
        return "success";
    }

    @RequestMapping("/testResponseBodyUser")
    @ResponseBody
    //要引入jackson依赖
    public User testResponseBodyUser(){
        return new User(1001,"admin","123456",18,"男");
    }

    @RequestMapping("/testAxios")
    @ResponseBody
    public  String testAxios(String username, String password){
        System.out.println(username+","+password);
        return "hello,axios";
    }
}
