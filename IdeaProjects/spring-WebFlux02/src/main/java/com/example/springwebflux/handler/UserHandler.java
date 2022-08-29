package com.example.springwebflux.handler;

import com.example.springwebflux.entiy.User;
import com.example.springwebflux.service.UserService;
import com.fasterxml.jackson.datatype.jsr310.deser.InstantDeserializer;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;

/**
 * author ye
 * createDate 2022/4/19  14:13
 */
public class UserHandler {
    private final UserService userService;

    public UserHandler(UserService userService) {
        this.userService = userService;
    }

    //根据id查询
    public Mono<ServerResponse> getUserById(ServerRequest request){
        //获取id
        int id = Integer.parseInt(request.pathVariable("id"));
        //控制处理
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        //获取数据
        Mono<User> userById = userService.getUserById(id);
        //转换数据
        return userById.flatMap(person -> ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromObject(person))
                .switchIfEmpty(notFound)
        );
    }

    //查询所有
    public Mono<ServerResponse> getAllUsers(ServerRequest request){
        Flux<User> users = this.userService.getAll();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(users, User.class);
    }

    //添加
    public Mono<ServerResponse> saveUser(ServerRequest request){
        Mono<User> mono = request.bodyToMono(User.class);
        return ServerResponse.ok().build(this.userService.saveUser(mono));
    }
}
