package com.example.springbootstartertest.Controller;

import com.example.hello.Service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired
    HelloService helloService;
    @RequestMapping("/")
    public String sayHello(){
        return helloService.sayHello("YQ");
    }
}
