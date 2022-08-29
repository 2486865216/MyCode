package test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class MethodTest {
    public static void main(String[] args) throws ClassNotFoundException {
        Class user = Class.forName("test01.User");

        Method[] methods = user.getDeclaredMethods();
        for (Method method:methods){
            System.out.println(Modifier.toString(method.getModifiers()));
            System.out.println(method.getReturnType());
            System.out.println(method.getName());
//            方法的修饰符列表(一个方法的参数可能有多个)
            Class[] parameterTypes = method.getParameterTypes();
            for (Class parameterType:parameterTypes){
                System.out.println(parameterType.getSimpleName());
            }
        }
    }
}
