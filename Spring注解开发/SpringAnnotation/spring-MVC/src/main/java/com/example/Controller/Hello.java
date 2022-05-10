package com.example.Controller;

import com.example.Service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * author ye
 * createDate 2022/3/17  12:40
 */
@Controller
public class Hello {

    @Autowired
    HelloService helloService;

    @RequestMapping("/")
    public String index(){
        return "index";
    }
    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){
        return helloService.sayHello();
    }
    @RequestMapping("success")
    public String success(){
        return "success";
    }
}
