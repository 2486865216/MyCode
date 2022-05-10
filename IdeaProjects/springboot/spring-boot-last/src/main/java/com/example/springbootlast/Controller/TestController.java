package com.example.springbootlast.Controller;

import com.example.springbootlast.Bean.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    Person person;

    @Value("${person.name:lisi}")
    String name;

    @Value("${JAVA_HOME}")
    String javaHome;

    @Value("${os.name}")
    String OsName;

    @RequestMapping("/")
    public Person TestController(){
        return person;
    }
    @RequestMapping("/test")
    public String TestControllerPerson(){
        return person.getClass().toString();
    }
    @RequestMapping("/home")
    public String home(){
        return javaHome+"==>"+OsName;
    }
}
