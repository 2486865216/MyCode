package testdom;


import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import serve.UserServe;

public class TestServe {
    @Test
    public void testserve(){
        ApplicationContext context = new ClassPathXmlApplicationContext("serve.xml");
        UserServe userServe = context.getBean("serve",UserServe.class);
        userServe.add();
    }
}
