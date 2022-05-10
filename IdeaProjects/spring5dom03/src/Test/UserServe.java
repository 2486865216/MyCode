package Test;

import org.springframework.stereotype.Component;

        //Spring 针对 Bean中创建对象提供注解
        //（1）@Component
        //（2）@Service
        //（3）@Controllers
        //（4）@Repositorye
        //上面四个注解功能是一样的，都可以用来创建bean实例。
//在注解里面value属性值可以不写
//默认值是类名称，首字母小写
//UserServe->userServe
@Component(value = "userServe")//<bean id="userServe" class="..."></bean>
public class UserServe {
    public void test(){
        System.out.println("hello");
    }
}
