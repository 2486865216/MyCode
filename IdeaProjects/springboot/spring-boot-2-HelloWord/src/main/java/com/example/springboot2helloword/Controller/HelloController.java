package com.example.springboot2helloword.Controller;


import com.example.springboot2helloword.Bean.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Period;

@RestController
public class HelloController {

    @Autowired
    Person person;
    @RequestMapping("/person")
    public Person person(){
        return person;
    }
}
