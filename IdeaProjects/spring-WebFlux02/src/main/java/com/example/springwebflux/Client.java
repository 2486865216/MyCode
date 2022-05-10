package com.example.springwebflux;

import com.example.springwebflux.entiy.User;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * author ye
 * createDate 2022/4/19  15:00
 */
public class Client {
    public static void main(String[] args) {
        WebClient webClient = WebClient.create("http://localhost:56782");

        String id  = "1";
        User block = webClient.get().uri("/user/{id}", id)
                .accept(MediaType.APPLICATION_JSON).retrieve()
                .bodyToMono(User.class)
                .block();
        System.out.println(block);

        //查询所有
        Flux<User> mono = webClient.get().uri("/getAll")
                .accept(MediaType.APPLICATION_JSON).retrieve()
                .bodyToFlux(User.class);

        mono.map(person -> person).buffer().doOnNext(System.out::println).blockFirst();
    }
}
