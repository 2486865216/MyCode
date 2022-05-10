package com.Dancebytes.spring5.springNew;


import com.Dancebytes.spring5.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

//@ExtendWith(SpringExtension.class)
//@ContextConfiguration("classpath:bean.xml")//加载配置文件
@SpringJUnitConfig(locations = "classpath:bean.xml")
public class Test305 {
    @Autowired
    private UserService userService;
    @Test
    public void test(){
        userService.accontMoney();
    }
}
