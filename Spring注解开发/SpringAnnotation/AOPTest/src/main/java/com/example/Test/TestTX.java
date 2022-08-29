package com.example.Test;

import com.example.Config.TXConfig;
import com.example.Service.UserService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * author ye
 * createDate 2022/3/16  12:45
 */
public class TestTX {
    @Test
    public void test() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TXConfig.class);
        UserService bean = context.getBean(UserService.class);
        bean.insert();
    }
}
