package test;

import java.lang.reflect.Method;

/*通过反射机制调用方法*/
public class test05 {
    public static void main(String[] args) throws Exception {
        Class user = Class.forName("test01.User");
        Object obj = user.newInstance();
        Method login = user.getDeclaredMethod("login",String.class,String.class);
//        invoke调用
        Object retvalue = login.invoke(obj,"abc","123");
        System.out.println(retvalue);
    }
}
