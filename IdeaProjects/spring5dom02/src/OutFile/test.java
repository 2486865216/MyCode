package OutFile;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class test {
    @Test
    public void tests1(){
        ApplicationContext context = new ClassPathXmlApplicationContext("OutFile/bean.xml");
        main m = context.getBean("main",main.class);
        m.test();
    }
}
