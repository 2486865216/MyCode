package com.yuye.Signleton;

/**
 * 单例模式
 * author ye
 * createDate 2022/1/26  11:30
 */
public class SignletonTest02 {
    public static void main(String[] args) {
        SingleTon1 instance = SingleTon1.getInstance();
        SingleTon1 instance2 = SingleTon1.getInstance();
        System.out.println(instance==instance2);
    }
}

//饿汉式（静态变量）
class SingleTon1{
    //构造器私有化，外部能new
    private SingleTon1(){

    }
    //在静态代码块中创建单例模式
    private static SingleTon1 instanceStatic;
    static {
        instanceStatic = new SingleTon1();
    }
    //提供一个共有的静态方法，返回实例对象
    public static SingleTon1 getInstance(){
        return instanceStatic;
    }
}
