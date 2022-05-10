package com.example.springwebflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringWebFluxApplication {
    /**
     * SpringMVC方式实现，同步阻塞的方式，基于SpringMVC+Servlet+-Tomcat.
     * SpringWebflux方式实现，异步非阻塞方式，基于SpringWebflux+Reactor+Netty
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(SpringWebFluxApplication.class, args);
    }

}
