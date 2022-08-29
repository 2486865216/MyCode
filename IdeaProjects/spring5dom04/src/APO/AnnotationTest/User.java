package APO.AnnotationTest;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

//被增强的类
@Component(value = "user")
public class User {
    public void add(){
        System.out.println("add.........");
    }
}
