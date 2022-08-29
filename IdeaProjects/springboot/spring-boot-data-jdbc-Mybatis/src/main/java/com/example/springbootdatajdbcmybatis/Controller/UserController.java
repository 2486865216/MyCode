package com.example.springbootdatajdbcmybatis.Controller;

import com.example.springbootdatajdbcmybatis.Bean.User;
import com.example.springbootdatajdbcmybatis.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @RequestMapping("/user")
    @ResponseBody
    public User getUser(@RequestParam("id") Integer id){
        return userService.getUser(id);
    }
}
