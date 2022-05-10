package com.example.springbootjunit5.EndPoint;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;

@Component
@Endpoint(id = "myendpoint")
public class MyEndPoint {
    @ReadOperation
    public Map getDockerInfo(){
        return Collections.singletonMap("dockerInfo", "docker started ...");
    }
    @WriteOperation
    public void stopDockerInfo(){
        System.out.println("docker stopped...");
    }
}
