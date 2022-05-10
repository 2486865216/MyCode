package com.yuye.Segregation;

/**
 * author ye
 * createDate 2022/1/25  10:53
 */
public class Segregation01 {
    public static void main(String[] args) {
        A a = new A();
        a.denpend1(new B());
        a.denpend2(new B());
        a.denpend3(new B());
        C c = new C();
        c.denpend1(new D());
        c.denpend4(new D());
        c.denpend5(new D());
    }
}
interface test1{
    void operation1();
    void operation2();
    void operation3();
    void operation4();
    void operation5();
}
class B implements test1{

    @Override
    public void operation1() {
        System.out.println("B implements operation1");
    }

    @Override
    public void operation2() {
        System.out.println("B implements operation2");
    }

    @Override
    public void operation3() {
        System.out.println("B implements operation3");
    }

    @Override
    public void operation4() {
        System.out.println("B implements operation4");
    }

    @Override
    public void operation5() {
        System.out.println("B implements operation5");
    }
}
//A依赖（使用）B类，但只使用1，2，3.
class A{

    public void denpend1(test1 i){
        i.operation1();
    }
    public void denpend2(test1 i){
        i.operation2();
    }
    public void denpend3(test1 i){
        i.operation3();
    }
}
class D implements test1{

    @Override
    public void operation1() {
        System.out.println("D implements operation1");
    }

    @Override
    public void operation2() {
        System.out.println("D implements operation2");
    }

    @Override
    public void operation3() {
        System.out.println("D implements operation3");
    }

    @Override
    public void operation4() {
        System.out.println("D implements operation4");
    }

    @Override
    public void operation5() {
        System.out.println("D implements operation5");
    }
}
//C依赖（使用）D类，但只使用1，4，5.
class C{

    public void denpend1(test1 i){
        i.operation1();
    }
    public void denpend4(test1 i){
        i.operation4();
    }
    public void denpend5(test1 i){
        i.operation5();
    }
}