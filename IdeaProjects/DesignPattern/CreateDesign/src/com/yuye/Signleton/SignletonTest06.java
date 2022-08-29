package com.yuye.Signleton;

import com.sun.xml.internal.bind.v2.model.core.ID;

/**
 * author ye
 * createDate 2022/1/26  11:46
 */
public class SignletonTest06 {
    public static void main(String[] args) {
        System.out.println("懒汉式，线程安全");
        Signleton06 instance = Signleton06.getInstance();
        Signleton06 instance1 = Signleton06.getInstance();
        System.out.println(instance1 == instance);
    }
}

class Signleton06{
    private Signleton06(){

    }
    private static volatile Signleton06 instanceTest01;
    //加入双重检查代码，解决线程安全问题，同时解决加载问题
    //同时保证了效率，推荐使用
    public static Signleton06 getInstance(){
        if (instanceTest01 == null){
            synchronized (Signleton06.class){
                if (instanceTest01 == null) {
                    instanceTest01 = new Signleton06();
                }
            }
        }
        return instanceTest01;
    }
}
