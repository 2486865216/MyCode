package com.qiqi.boot.Controller;

import com.qiqi.boot.bean.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@ResponseBody
//@Controller

@RestController
public class HelloController {

    @Autowired
    Car car;

    @RequestMapping("/hello")
    public String hello(){
        return "hello,Spring Boot 2!";
    }


    @RequestMapping("/car")
    public Car car(){
        return car;
    }
}
