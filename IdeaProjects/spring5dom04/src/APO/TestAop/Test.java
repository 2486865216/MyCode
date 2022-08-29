package APO.TestAop;

import APO.AnnotationTest.Configs;
import APO.AnnotationTest.User;
import APO.Zhujie.Userbuy;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
   @org.junit.Test
    public void test01(){
       ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
       User user = context.getBean("user", APO.AnnotationTest.User.class);
       user.add();
    }
    @org.junit.Test
    public void test02(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean2.xml");
        Userbuy user = context.getBean("user", Userbuy.class);
        user.buy();
    }
    @org.junit.Test
    public void test03(){
        ApplicationContext context = new AnnotationConfigApplicationContext(Configs.class);
        User user = context.getBean("user", User.class);
        user.add();
    }
}
