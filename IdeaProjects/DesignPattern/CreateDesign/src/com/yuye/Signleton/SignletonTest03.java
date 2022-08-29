package com.yuye.Signleton;

/**
 * author ye
 * createDate 2022/1/26  11:46
 */
public class SignletonTest03 {
    public static void main(String[] args) {
        System.out.println("懒汉式，线程不安全");
        Signleton03 instance = Signleton03.getInstance();
        Signleton03 instance1 = Signleton03.getInstance();
        System.out.println(instance1 == instance);
    }
}
class Signleton03{
    private Signleton03(){

    }
    private static Signleton03 instanceTest01;
    //当使用该方法时才去创建（懒汉式）
    public static Signleton03 getInstance(){
        if (instanceTest01 == null){
            instanceTest01 = new Signleton03();
        }
        return instanceTest01;
    }
}
