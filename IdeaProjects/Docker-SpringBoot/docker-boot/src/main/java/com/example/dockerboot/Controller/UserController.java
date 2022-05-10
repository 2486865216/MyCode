package com.example.dockerboot.Controller;

import com.example.dockerboot.Bean.User;
import com.example.dockerboot.Service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Random;
import java.util.UUID;

/**
 * author ye
 * createDate 2022/3/10  14:29
 */
@RestController
@Slf4j
public class UserController {
    @Resource
    private UserService userService;
    @GetMapping("/user/add")
    public void addUser(){
        for (int i = 0; i < 3; i++) {
            User user = new User();
            user.setUsername("zhangyuye" + i);
            user.setPassword(UUID.randomUUID().toString().substring(0,6));
            user.setSex((byte)new Random().nextInt(2));
            userService.addUser(user);
        }
    }
    @GetMapping("/user/find/{id}")
    public User findUserById(@PathVariable("id") Integer id){
        return userService.getUser(id);
    }
}
