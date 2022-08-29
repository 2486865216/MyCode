package com.example.dockerboot.Controller;

import com.example.dockerboot.Bean.User;
import com.example.dockerboot.Service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * author ye
 * createDate 2022/3/9  21:29
 */
@RestController
@Api(tags = "首页模块")
public class HelloWorld {
    @Value("${server.port}")
    String port;

    @Autowired
    UserService userService;

    @ApiOperation("testkkk")
    @GetMapping("/order/docker")
    public String helloServer(){
        return "服务已启动：" + port + UUID.randomUUID().toString();
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") Integer id){
        return userService.getUser(id);
    }
}
