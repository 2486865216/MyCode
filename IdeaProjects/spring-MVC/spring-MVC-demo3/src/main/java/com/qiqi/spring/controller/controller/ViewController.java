package com.qiqi.spring.controller.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {
    @RequestMapping("/testThymeleaf")
    public String testThymeleaf(){
        return "success";
    }
    @RequestMapping("/testForward")
    public String testForward(){
        return "forward:/testThymeleaf";
    }
    @RequestMapping("/testRedirect")
    public String testRedirect(){
        return "redirect:/testThymeleaf";
    }
}
