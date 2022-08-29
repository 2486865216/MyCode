package com.qiqi.spring.controller;

import com.qiqi.spring.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class jspController {
    @RequestMapping("/success")
    public String success(){
        return "success";
    }

    @RequestMapping("/user")
    @ResponseBody
    public User user(){
        return new User(1, "hello");
    }
}
