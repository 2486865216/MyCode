package com.yuye.Segregation;

import sun.java2d.pipe.SpanIterator;

/**
 * author ye
 * createDate 2022/1/25  11:07
 */
public class Segregation02 {
    public static void main(String[] args) {
        A1 a1 = new A1();
        a1.dependency1(new B1());
        a1.dependency2(new B1());
        a1.dependency3(new B1());

        C1 c1= new C1();
        c1.dependency1(new D1());
        c1.dependency4(new D1());
        c1.dependency5(new D1());
    }
}
interface interface01{
    void operation1();
}
interface interface02{
    void operation2();
    void operation3();
}
interface interface03{
    void operation4();
    void operation5();
}
class B1 implements interface01,interface02{

    @Override
    public void operation1() {
        System.out.println("B1 implements operation01");
    }

    @Override
    public void operation2() {
        System.out.println("B1 implements operation02");
    }

    @Override
    public void operation3() {
        System.out.println("B1 implements operation03");
    }
}
class A1{
    public void dependency1(interface01 interface01){
        interface01.operation1();
    }
    public void dependency2(interface02 interface02){
        interface02.operation2();
    }
    public void dependency3(interface02 interface02){
        interface02.operation3();
    }
}
class D1 implements interface01,interface03{

    @Override
    public void operation1() {
        System.out.println("D1 implements operation01");
    }

    @Override
    public void operation4() {
        System.out.println("D1 implements operation04");
    }

    @Override
    public void operation5() {
        System.out.println("D1 implements operation05");
    }
}
class C1{
    public void dependency1(interface01 interface01){
        interface01.operation1();
    }
    public void dependency4(interface03 interface03){
        interface03.operation4();
    }
    public void dependency5(interface03 interface03){
        interface03.operation5();
    }
}