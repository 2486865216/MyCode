package com.yuye.Liskov;


/**
 * author ye
 * createDate 2022/1/25  13:50
 */
public class Liskov02 {
    public static void main(String[] args) {
    }
}
//基础类
class Base{
    //把更基础的成员和方法写到base类
}
class A1 extends Base{
    public int fun1(int a, int b){
        return a-b;
    }
}
class B1 extends Base{
    public int fun1(int a, int b){
        return a + b;
    }
    public int fun2(int a, int b){
        return fun1(a, b) + 9;
    }
    //如果B需要使用A的方法，可以使用组合关系
    private A1 a = new A1();
    public int fun3(int a, int b){
        return this.a.fun1(a, b);
    }
}

