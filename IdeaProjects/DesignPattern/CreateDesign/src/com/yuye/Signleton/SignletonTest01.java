package com.yuye.Signleton;

/**
 * 单例模式
 * author ye
 * createDate 2022/1/26  11:30
 */
public class SignletonTest01 {
    public static void main(String[] args) {
        SingleTon instance = SingleTon.getInstance();
        SingleTon instance2 = SingleTon.getInstance();
        System.out.println(instance==instance2);
    }
}
//饿汉式（静态变量）
class SingleTon{
    //构造器私有化，外部能new
    private SingleTon(){

    }
    //本类内部创建实例对象
    private final static SingleTon instance = new SingleTon();
    //提供一个共有的静态方法，返回实例对象
    public static SingleTon getInstance(){
        return instance;
    }
}
