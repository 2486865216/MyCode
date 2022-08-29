package com.Dancebytes.spring5.springNew;

import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Test2 {
    //函数式风格创建对象，交给spring进行管理
    @Test
    public void testGenericApplicationContext(){
        //创建GenericXmlApplicationContext对象
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
    //    调用context方法
        context.refresh();
        context.registerBean(User.class,() -> new User());
    //    获取spring注册的对象
        User user = (User) context.getBean("com.Dancebytes.spring5.springNew.User");
        System.out.println(user);
    }
}
