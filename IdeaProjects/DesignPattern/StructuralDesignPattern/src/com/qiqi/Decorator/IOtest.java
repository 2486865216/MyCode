package com.qiqi.Decorator;

import java.io.DataInputStream;
import java.io.FileInputStream;

/**
 * author ye
 * createDate 2022/2/6  12:13
 */
public class IOtest {
    public static void main(String[] args) throws Exception{
        /**
         * 1,InputStream是抽象类，类似于前面的Drink
         * 2,FileInputStream 是 InputStream 的子类，类似于前面的 LongBlack，ShortBlack，Espresso
         * 3,FilterInputStream 是 InputStream 的子类，类似于前面的 DecoratorTest,修饰者
         * 4,DataInputStream 是 FilterInputStream 的子类，类似于前面的 Milk，Soy，Chocolate，具体的修饰者
         * 5,FilterInputStream有protected volatile InputStream in; 含有被修饰者
         * 6,jdk中io使用装饰者模式
         */
        DataInputStream dis = new DataInputStream(new FileInputStream("xxx"));
        dis.read();
        dis.close();
    }
}
