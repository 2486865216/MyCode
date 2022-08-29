package com.example.springbootweb01.Controller;

import com.example.springbootweb01.Bean.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TestBasedAnnation {
    //car/2/owner/zhangsan
    @GetMapping("/car/{id}/owner/{name}")
    public Map<String, Object> getCar(@PathVariable("id") Integer id,
                                      @PathVariable("name") String name,
                                      @PathVariable Map<String, Object> pv){
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("name", name);
        map.put("pv", pv);
        return map;
    }
    @GetMapping("/getRequestHeader")
    public Map<String, Object> getRequestHeader(@RequestHeader Map<String, String> header){
        Map<String, Object> map = new HashMap<>();
        map.put("header", header);
        return map;
    }
    @GetMapping("/RequestParam")
    public Map<String, Object> getRequestParam(@RequestParam("age") Integer age,
                                               @RequestParam("interests") List<String> list,
                                               @RequestParam Map<String, String> interests) {
        Map<String, Object> map = new HashMap<>();
        map.put("age", age);
        map.put("interests", list);
        map.put("map", interests);
        return map;
    }
    @GetMapping("/CookieValue")
    public Map<String, Object> getRequestCookie(@CookieValue("_ga") Cookie cookie) {
        Map<String, Object> map = new HashMap<>();
        System.out.println(cookie.getValue());
        return map;
    }
    @PostMapping("/save")
    public Map<String, Object> getRequestAttribute(@RequestBody String content){
        Map<String, Object> map = new HashMap<>();
        map.put("content", content);
        return map;
    }
    // /cars/sell;low=34;brand=byd,audi,yd
    //矩阵变量需要在SpringBoot中手动开启
    @GetMapping("/cars/{path}")
    public Map<String, Object> carsSell(@MatrixVariable("low") Integer low,
                                        @MatrixVariable("brand") List<String> brand,
                                        @PathVariable("path") String path){
        Map<String, Object> map = new HashMap<>();
        map.put("low", low);
        map.put("brand", brand);
        map.put("path", path);
        return map;
    }
    // /boss/1;age=20/2;age=10
    @GetMapping("/boss/{Id1}/{Id2}")
    public Map<String, Object> boss(@MatrixVariable(value = "age", pathVar = "Id1") Integer age1,
                                        @MatrixVariable(value = "age", pathVar = "Id2") Integer age2){
        Map<String, Object> map = new HashMap<>();
        map.put("bossId", age1);
        map.put("empId", age2);
        return map;
    }

    @PostMapping("/saveuser")
    public User user(User user){
        return user;
    }
}
