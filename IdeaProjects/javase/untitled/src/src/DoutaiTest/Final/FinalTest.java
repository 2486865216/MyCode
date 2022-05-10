package src.DoutaiTest.Final;

public class FinalTest {
    /*关于java语言当中final关键字：
        1、final是一个关键字，表示最终的，不可变的。
        2、final修饰的类无法被继承
        3、final修饰的方法无法被覆盖
        4、final修饰的变量一旦赋值之后，不可重新赋值
        5、final修饰的实例变量,手动赋值，不能采用系统默认值
        6、final修饰的引用,一旦指向某个对象之后，不能指向其它对象，对象内部的内存可以修改
        7、final修饰的实例变量，一般和static联合使用，被称为“常量”
    */
    public static void main(String[] args) {
        System.out.println(Person.GUO_JI);
    }
    static class Person{
        /*final + static = 常量
         * 名字全部大写，中间用下划线连接*/
       public static final String GUO_JI = "中国";
    }
}
