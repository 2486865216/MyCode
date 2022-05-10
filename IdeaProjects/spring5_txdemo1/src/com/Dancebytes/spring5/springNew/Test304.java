package com.Dancebytes.spring5.springNew;

import com.Dancebytes.spring5.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:bean.xml")//加载配置文件
public class Test304 {
    @Autowired
    private UserService userService;
    @Test
    public void test(){
        userService.accontMoney();
    }
}
