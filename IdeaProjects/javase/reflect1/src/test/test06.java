package test;

import java.lang.reflect.Constructor;

/*调用构造方法*/
public class test06 {
    public static void main(String[] args) throws Exception {
        Class vip = Class.forName("test01.Vip");
        Object onj = vip.newInstance();
//        获取有参数的构造方法
        Constructor con = vip.getDeclaredConstructor(int.class,int.class);
//        调用有参构造方法new对象
        Object newObj = con.newInstance(10,20);
        System.out.println(newObj);
    }
}
