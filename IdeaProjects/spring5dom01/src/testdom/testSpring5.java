package testdom;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring5.Book;
import spring5.Order;
import spring5.User;
/*
*   1、IOC思想基于IOC容器完成，IOC容器底层就是对象工厂v
    2、Spring提供IOC容器实现两种方式： (两个接口)
        (1)BeanFactory:IOC容器基本实现，是Spring内部的使用接口，
        * 不提供开发人员进行使用。加载配置文件时候不会创建对象，
        * 在获取对象(使用)才去创建对象
    （2）ApplicationContext:BeanFactory接口的子接口，
        * 提供更多更强大的功能，一般由开发人员进行使用、加载配置文件时候就
        * 会把在配置文件对象进行创建。*/
public class testSpring5 {
    @Test
    public void testAdd(){
        //加载spring配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        //获取配置文件
        User user = context.getBean("user", User.class);
        System.out.println(user);
        user.add();
    }
    @Test
    public void testBook() {
        ApplicationContext context = new ClassPathXmlApplicationContext("book.xml");
        Book book = context.getBean("book", Book.class);
        System.out.println(book);
        book.testDom();
    }
    @Test
    public void testOrder() {
        ApplicationContext context = new ClassPathXmlApplicationContext("Order.xml");
        Order order = context.getBean("order", Order.class);
        System.out.println(order);
        order.test();
    }
}
