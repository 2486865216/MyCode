package com.yuye.Signleton;

/**
 * author ye
 * createDate 2022/1/26  11:46
 */
public class SignletonTest04 {
    public static void main(String[] args) {
        System.out.println("懒汉式，线程安全");
        Signleton04 instance = Signleton04.getInstance();
        Signleton04 instance1 = Signleton04.getInstance();
        System.out.println(instance1 == instance);
    }
}

class Signleton04{
    private Signleton04(){

    }
    private static Signleton04 instanceTest01;
    //当使用该方法时才去创建（懒汉式）
    //加入同步处理的代码
    public static synchronized Signleton04 getInstance(){
        if (instanceTest01 == null){
            instanceTest01 = new Signleton04();
        }
        return instanceTest01;
    }
}
