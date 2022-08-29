package com.qiqi.spring.controller.controller;

import com.qiqi.spring.controller.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

@Controller
public class ParamController {

    @RequestMapping("/testServletAPI")
    //形参位置的request表示当前请求
    public String testServletAPI(HttpServletRequest request){
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("username:"+username+"   password:"+password);
        return "success";
    }

    @RequestMapping("/testparam")
    public String testParam(
            @RequestParam(value = "user_name" ,required = false, defaultValue = "hello") String username,
            String password,
            String[] hobby,
            //required是否必须传递值，默认为true,没有传输或没有设置defaultValue则报错400
            //多请求参数中出现多个同名的请求参数，
            // 可以在控制器方法的形参位置设置字符串类型或字符串数组接受请求参数
            //若使用字符串类型的形参，最终结果为请求参数的每一个值之间用逗号分隔
            @RequestHeader("Host") String host,
            @CookieValue("JSESSIONID") String jessionid
            ){
        System.out.println("username:"+username+"   password:"+password+"   hobby:"+ Arrays.toString(hobby));
        System.out.println("host:"+host);
        System.out.println("JESSIONID:"+jessionid);
        return "success";
    }

    @RequestMapping("/testPOJO")
    public String testPOJO(User user){
        System.out.println(user);
        return "success";
    }
}
