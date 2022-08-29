package com.example.springwebflux.controller;

import com.example.springwebflux.entiy.User;
import com.example.springwebflux.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.websocket.server.PathParam;

/**
 * author ye
 * createDate 2022/4/19  13:44
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    //id查询
    @GetMapping("/user/{id}")
    public Mono<User> getById(@PathVariable("id") Integer id){
        return userService.getUserById(id);
    }

    //查询所有
    @GetMapping("/getAll")
    public Flux<User> getAll(){
        return userService.getAll();
    }

    //添加
    @PostMapping("/saveUser")
    public Mono<Void> savaUser(@RequestBody User user){
        Mono<User> mono = Mono.just(user);
        return userService.saveUser(mono);
    }
}
