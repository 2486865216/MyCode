package APO.AnnotationTest;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(3)
public class PersonProxy {
    @Before(value = "execution(* APO.AnnotationTest.User.add(..))")
    public void before(){
        System.out.println("personProxy before....");
    }
}
