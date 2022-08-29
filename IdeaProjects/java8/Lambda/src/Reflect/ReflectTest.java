package Reflect;

import org.junit.Test;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Properties;

public class ReflectTest {
    @Test
    public void test() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Class user = User.class;
        System.out.println(user);
        Object userObject = user.newInstance();
        Method userMethod = user.getDeclaredMethod("sayHello");
        userMethod.setAccessible(true);
        userMethod.invoke(userObject);
        Method userMethod2 = user.getDeclaredMethod("sayHi", String.class);
        userMethod2.setAccessible(true);
        userMethod2.invoke(userObject,"qiqi");

        Class user2 = Class.forName("Reflect.User");
        System.out.println(user2);

        User user3 = new User();
        Class user3Class = user3.getClass();
        System.out.println(user3Class);

        ClassLoader classLoader = User.class.getClassLoader();
        Class user4 = classLoader.loadClass("Reflect.User");
        System.out.println(user4);

        int[] a = new int[10];
        int[] b = new int[100];
        System.out.println(a.getClass()==b.getClass());
    }

    @Test
    public void test2() throws IOException {
        //默认Model下
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(new File("jdbc.properties"));
        properties.load(fileInputStream);
        System.out.println(properties.getProperty("user"));
        System.out.println(properties.getProperty("password"));

        //默认src下
        Properties properties1 = new Properties();
        ClassLoader classLoader = this.getClass().getClassLoader();
        InputStream resourceAsStream = classLoader.getResourceAsStream("jdbc1.properties");
        properties1.load(resourceAsStream);
        System.out.println(properties1.getProperty("user"));
        System.out.println(properties1.getProperty("password"));

        String java2 = this.getClass().getClassLoader().getResource("Reflect").getFile();
        System.out.println(java2);
    }
}
