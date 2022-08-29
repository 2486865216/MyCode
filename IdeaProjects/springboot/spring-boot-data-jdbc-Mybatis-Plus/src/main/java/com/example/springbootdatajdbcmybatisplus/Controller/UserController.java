package com.example.springbootdatajdbcmybatisplus.Controller;

import com.example.springbootdatajdbcmybatisplus.Bean.User;
import com.example.springbootdatajdbcmybatisplus.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/user")
    @ResponseBody
    public User getUser(Integer id){
        return userService.getById(id);
    }
    @RequestMapping("/users")
    @ResponseBody
    public Map<String,User> getUsers(){
        Map<String,User> map = new HashMap<>();
        List<User> list = userService.list();
        for (User user : list) {
            map.put(user.getId().toString(), user);
        }
        return map;
    }
}
