package com.example.springwebflux.service.serviceImpl;

import com.example.springwebflux.entiy.User;
import com.example.springwebflux.service.UserService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * author ye
 * createDate 2022/4/19  13:38
 */
@Service
public class userImpl implements UserService {
    //创建map存储数据
    private final Map<Integer, User> users = new HashMap<>();

    public userImpl() {
        this.users.put(1, new User("hello", "男", 18));
        this.users.put(2, new User("spring", "男", 18));
        this.users.put(3, new User("java", "男", 18));
    }

    @Override
    public Mono<User> getUserById(int id) {
        return Mono.justOrEmpty(this.users.get(id));
    }

    @Override
    public Flux<User> getAll() {
        return Flux.fromIterable(this.users.values());
    }

    @Override
    public Mono<Void> saveUser(Mono<User> user) {
        return user.doOnNext(person -> {
            int size = users.size() + 1;
            users.put(size, person);
        }).thenEmpty(Mono.empty());
    }
}
