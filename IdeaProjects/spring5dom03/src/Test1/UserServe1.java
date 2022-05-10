package Test1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class UserServe1 {
    //注入普通类型属性
    @Value(value = "abc")
    private String name;
    //不需要添加set方法
    //根据属性类型进行自动装配
    /*@Autowired*/
    //根据属性名称进行注入
    //和@Autowired一起使用
    //有多个类的时候用@Qualifier根据属性名称进行注入
    /*@Qualifier(value="userDao1")*/

    /*@Resource //根据类型进行注入*/
    @Resource(name = "userDao")//根据名称进行注入
    private User user;

    public void add(){
        System.out.println(name);
        System.out.println("UserServe add ..........");
        user.add();
    }
}
