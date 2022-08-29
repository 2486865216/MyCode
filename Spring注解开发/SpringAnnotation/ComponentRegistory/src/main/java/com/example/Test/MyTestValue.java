package com.example.Test;

import com.example.Bean.Person;
import com.example.Config.MyConfigOPropertyValue;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * author ye
 * createDate 2022/3/15  9:26
 */
public class MyTestValue {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfigOPropertyValue.class);
    @Test
    public void test() {
        Person person = (Person) context.getBean("person");
        System.out.println(person);
    }
}
