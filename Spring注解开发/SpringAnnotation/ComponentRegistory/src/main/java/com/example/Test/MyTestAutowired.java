package com.example.Test;

import com.example.Bean.Boss;
import com.example.Bean.Car;
import com.example.Config.MyConfigAutowired;
import com.example.Dao.DaoTest;
import com.example.Service.ServiceTest;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * author ye
 * createDate 2022/3/15  9:36
 */
public class MyTestAutowired {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfigAutowired.class);
    @Test
    public void test() {
        ServiceTest bean = context.getBean(ServiceTest.class);
        System.out.println(bean);
        DaoTest bean1 = context.getBean(DaoTest.class);
        System.out.println(bean1);
        System.out.println(bean.getDaoTest().getLabel());
    }

    @Test
    public void test1() {
        Boss bean = context.getBean(Boss.class);
        System.out.println(bean);
        System.out.println(bean.getCar());
        System.out.println(context.getBean(Car.class));
    }
}
