package com.qiqi.spring.controller.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
//@RequestMapping("/hello")
public class RequsetMappingController {

    @RequestMapping(
            value = {"/testsuccess", "test"},
            method = {RequestMethod.GET,RequestMethod.POST}
    )
    public String success(){
        return "success";
    }
    @GetMapping("testGetmapping")
    public String testGetMapping(){
        return "success";
    }

    @RequestMapping(
            value = "/testparemsandHeaders",
            params = {"username"},
            //params = {"!username"}
            //params = {"username=admin"}
            //params = {"username!=admin"}
            headers = {"Host=localhost:8081"}
    )
    public String testparemsandHeaders(){
        return "success";
    }

    /*
    * '?'匹配单个任意字符  /,?不行
    * '*'任意的零个或多个字符 '/'不行
    * '**'表示任意的一层或多层目录 在使用‘**’的时候只能使用'/‘**’/xxx'的写法
    * */
    //@RequestMapping("/a?a/testAnt")
    //@RequestMapping("/a/a/testAnt")
    @RequestMapping("/**/testAnt")
    public String AntTest(){
        return "success";
    }

    @RequestMapping("test/{id}/{username}")
    public String testPath(@PathVariable("id") Integer id, @PathVariable("username") String username){
        System.out.println("id:"+id+"  username:"+username);
        return "success";
    }
}
