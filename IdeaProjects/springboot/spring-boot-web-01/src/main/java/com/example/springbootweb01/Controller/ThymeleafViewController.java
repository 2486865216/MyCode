package com.example.springbootweb01.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ThymeleafViewController {

    @RequestMapping("/test")
    public String test(Model model){

        model.addAttribute("msg", "HelloWord!");
        model.addAttribute("link", "www.baidu.com");

        return "success";
    }
    @RequestMapping("/index")
    public String index(){
        return "redirect:/index.html";
    }
    @RequestMapping("/index.html")
    public String indexHtml(){
        return "index";
    }
}
