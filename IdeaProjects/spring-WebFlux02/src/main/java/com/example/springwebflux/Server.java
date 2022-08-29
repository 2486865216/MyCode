package com.example.springwebflux;

/**
 * author ye
 * createDate 2022/4/19  14:31
 */

import com.example.springwebflux.handler.UserHandler;
import com.example.springwebflux.service.UserService;
import com.example.springwebflux.service.serviceImpl.userImpl;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.netty.http.server.HttpServer;

import java.io.IOException;

import static org.springframework.web.reactive.function.server.RouterFunctions.toHttpHandler;

/**
 * 创建路由
 */
public class Server {
    //创建路由
    public RouterFunction<ServerResponse> routingFunction(){
        UserService userService = new userImpl();
        UserHandler userHandler = new UserHandler(userService);
        return RouterFunctions.route(
                RequestPredicates.GET("/user/{id}")
                        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), userHandler::getUserById
        ).andRoute(
                RequestPredicates.GET("/getAll")
                        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), userHandler::getAllUsers
        );
    }
    //创建服务器完成适配
    public void createReactorServer(){
        RouterFunction<ServerResponse> router = routingFunction();
        HttpHandler httpHandler = toHttpHandler(router);
        ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(httpHandler);

        //创建服务器
        HttpServer httpServer = HttpServer.create();
        httpServer.handle(adapter).bindNow();
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.createReactorServer();
        System.out.println("enter to exit");
        System.in.read();
    }
}
