package autowrite;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class test {
    @Test
    public void test1(){
        ApplicationContext c = new ClassPathXmlApplicationContext("autowrite/bean.xml");
        Emp e = c.getBean("emp",Emp.class);
        e.test();
    }
}
