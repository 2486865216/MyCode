package com.yuye.Liskov;

/**
 * author ye
 * createDate 2022/1/25  13:50
 */
public class Liskov01 {
    public static void main(String[] args) {
        A a = new A();
        System.out.println("11 - 3  "+a.fun1(11, 3));
        System.out.println("1 - 3  "+a.fun1(1, 3));
        System.out.println("=================");

        B b = new B();
        System.out.println("11 - 3  "+b.fun1(11, 3));
        System.out.println("1 - 3  "+b.fun1(1, 3));
    }
}
class A{
    public int fun1(int a, int b){
        return a-b;
    }
}
class B extends A{
    public int fun1(int a, int b){
        return a + b;
    }
    public int fun2(int a, int b){
        return fun1(a, b) + 9;
    }
}
