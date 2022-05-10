package test;
/*
        * 2、反射机制
        2.1、反射机制有什么用？
            通过java语言中的反射机制可以操作字节码文件。
            优点类似于黑客。 （可以读和修改字节码文件。）
            通过反射机制可以操作代码片段。(class文件。)
        2.2、反射机制的相关类在哪个包下？
            java.lang.reflect.*;
        2.3、反射机制相关的重要的类有哪些？
            java.lang.class：代表整个字节码，代表一个类型,类
            java.lang.reflect.Method:代表字节码中的方法字节码。类中的方法
            java.lang.reflect.Constructor：代表字节码中的构造方法字节码。类中的构造方法
            java.lang.reflect.Field：代表字节码中的属性字节码。类中的成员变量（静态变量）
*/
public class reflecttest {
    public static void main(String[] args) {
        /*
         * Class.forname
         * 静态方法
         * 方法的参数是一个字符串
         * 字符串需要的是一个完整类名
         * 完整类名必须带有包名，java.lang包也不能省略*/
        try {
            Class c1 = Class.forName("java.lang.String");
            Class c2 = Class.forName("java.util.Date");
            Class c3 = Class.forName("java.lang.Integer");

            /*
             * java任何一个对象都有getClass方法
             * */
            String s= "abc";
            Class x = s.getClass();
            System.out.println(c1 == x);

            /*java任何一个对象都有.Class属性*/

            Class c4 = String.class;

            System.out.println(c1==c4);

            Class<?> aClass = reflecttest.class.getClassLoader().loadClass("java.lang.String");
            System.out.println(aClass==c1);


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
