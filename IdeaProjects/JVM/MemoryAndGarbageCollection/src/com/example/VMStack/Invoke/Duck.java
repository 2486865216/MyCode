package com.example.VMStack.Invoke;

/**
 * author ye
 * createDate 2022/2/11  16:09
 */
public class Duck extends Animal implements Fly{
    public Duck() {
        super();
    }
    @Override
    public void run() {
        System.out.println("Duck run");
        doTest.test();
    }
    @Override
    public void doFly() {
        System.out.println("Duck Fly");
    }
}
