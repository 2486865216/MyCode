package com.qiqi.spring.controller.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
    /**restful 模拟用户的增删改查
     * /user     GET      查询所有用户信息
     * /user/1   GET      根据用户ID查询用户信息
     * /user     POST     添加用户信息
     * /user/1   DELETE   删除用户信息
     * /user     PUT      修改用户信息
     */
    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public String getAlluseer(){
        System.out.println("查询所有用户信息");
        return "success";
    }
    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
    public String getUserById(){
        System.out.println("根据ID查询用户信息");
        return "success";
    }
    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public String insertUser(String username,String password){
        System.out.println("添加用户信息："+username+","+password);
        return "success";
    }
    @RequestMapping(value = "/user",method = RequestMethod.PUT)
    public String updateUser(String username,String password){
        System.out.println("修改用户信息："+username+","+password);
        return "success";
    }
}
