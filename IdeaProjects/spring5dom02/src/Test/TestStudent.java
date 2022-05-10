package Test;

import collectiontype.Book;
import collectiontype.Course;
import collectiontype.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestStudent {
    @Test
    public void test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        Student student = context.getBean("student",Student.class);
        student.test();
    }
    @Test
    public void test2(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean2.xml");
        Book book1 = context.getBean("book",Book.class);
        Book book2 = context.getBean("book",Book.class);
//        book.test();
        System.out.println(book1);
        System.out.println(book2);
    }
    @Test
    public void test3(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean3.xml");
        Course course = context.getBean("myBean",Course.class);
        System.out.println(course);
    }
}
