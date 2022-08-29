package com.qiqi.Adapter.ClassAdapter;

import org.junit.Test;

/**
 * author ye
 * createDate 2022/1/27  13:23
 */
public class Client {
    @Test
    public void test() {
        System.out.println("类适配器");
        Phone phone = new Phone();
        phone.charging(new VoltageAdapter());
    }
}
