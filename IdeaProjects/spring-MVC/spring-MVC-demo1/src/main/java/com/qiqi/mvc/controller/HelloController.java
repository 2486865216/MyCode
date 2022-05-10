package com.qiqi.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
    //请求映射
    @RequestMapping("/")
    public String index(){
        //返回视图名称
        return "index";
    }
    @RequestMapping("/target")
    public String target(){
        return "target";
    }
}
