package com.example.springwebflux.service;

import com.example.springwebflux.entiy.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * author ye
 * createDate 2022/4/19  13:35
 */
public interface UserService {
    //根据id查询
    Mono<User> getUserById(int id);

    //查询所有用户
    Flux<User> getAll();

    //添加用户
    Mono<Void> saveUser(Mono<User> user);
}
