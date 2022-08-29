package com.example.springbootweb01.Controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    //@RequestMapping(value = "/user", method = RequestMethod.GET)
    @GetMapping("/user")
    public String getUser(){
        return "GET-张三";
    }
    //@RequestMapping(value = "/user", method = RequestMethod.POST)
    @PostMapping("/user")
    public String postUser(){
        return "POST-张三";
    }
    //@RequestMapping(value = "/user", method = RequestMethod.PUT)
    @PutMapping("/user")
    public String putUser(){
        return "PUT-张三";
    }
    //@RequestMapping(value = "/user", method = RequestMethod.DELETE)
    @DeleteMapping("/user")
    public String deleteUser(){
        return "DELETE-张三";
    }

    //扩展点：如何把method的名字换成我们喜欢的
    //web.config
}
