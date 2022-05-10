package bean生命周期;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class test {
    @Test
    public void test1(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean生命周期/beanOrder.xml");
        Order order = context.getBean("order",Order.class);
        System.out.println("第四步");
        System.out.println(order);
        context.close();
    }
}
