package testdom;

import bean.Emp;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestEmp {
    @Test
    public void test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean3.xml");
        Emp test = context.getBean("emp",Emp.class);
        test.add();
    }
    @Test
    public void test2(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean3-1.xml");
        Emp test = context.getBean("emp",Emp.class);
        test.add();
    }
}
