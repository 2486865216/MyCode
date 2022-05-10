package com.example.hello.Service;

import com.example.hello.Bean.HelloProperties;
import org.springframework.beans.factory.annotation.Autowired;

public class HelloService {

    @Autowired
    HelloProperties helloProperties;

    public String sayHello(String username){
        return helloProperties.getPrefix()+":"+username+">"+helloProperties.getSuffix();
    }
}
