package Test1;

import Test3.SpringConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
    5、基于注解方式实现属性注入.
        (1)@Autowired：
            根据属性类型进行自动装配
        （2)@Qualifier：
            根据属性名称进行注入
        （3)Resource：。
            可以根据类型注入
            可以根据名称注入
         (4)@Value
            注入普通类型属性
* */
public class test {
    @Test
    public void test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        UserServe1 userServe = context.getBean("userServe1", UserServe1.class);
        userServe.add();
    }
    @Test
    public void test3(){
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserServe1 userServe = context.getBean("userServe1", UserServe1.class);
        userServe.add();
    }
}
