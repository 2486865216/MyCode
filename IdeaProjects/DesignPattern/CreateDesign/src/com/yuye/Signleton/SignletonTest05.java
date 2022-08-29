package com.yuye.Signleton;

/**
 * author ye
 * createDate 2022/1/26  11:46
 */
public class SignletonTest05 {
    public static void main(String[] args) {
        System.out.println("懒汉式，线程安全");
        Signleton05 instance = Signleton05.getInstance();
        Signleton05 instance1 = Signleton05.getInstance();
        System.out.println(instance1 == instance);
    }
}

class Signleton05{
    private Signleton05(){

    }
    private static Signleton05 instanceTest01;
    //当使用该方法时才去创建（懒汉式）
    //加入同步处理的代码
    public static Signleton05 getInstance(){
        if (instanceTest01 == null){
            synchronized (Signleton05.class){
                instanceTest01 = new Signleton05();
            }
        }
        return instanceTest01;
    }
}
