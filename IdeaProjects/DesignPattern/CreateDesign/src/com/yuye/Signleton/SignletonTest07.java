package com.yuye.Signleton;

import org.omg.PortableInterceptor.INACTIVE;

/**
 * author ye
 * createDate 2022/1/26  11:46
 */
public class SignletonTest07 {
    public static void main(String[] args) {
        Signleton07 instance = Signleton07.getInstance();
        Signleton07 instance1 = Signleton07.getInstance();
        System.out.println(instance1 == instance);
    }
}
//静态内部类,推荐使用
class Signleton07{
    private Signleton07(){
    }
    //一个静态内部类，里面有一个静态属性
    private static class SingleTonInstance{
        private static final Signleton07 INSTANCE = new Signleton07();
    }
    public static synchronized Signleton07 getInstance(){
        return SingleTonInstance.INSTANCE;
    }
}
