package com.example.Callable;

import java.util.concurrent.Callable;

/**
 * author ye
 * createDate 2022/3/23  13:20
 */
public class CallableTest implements Callable {
    @Override
    public Object call() throws Exception {
        System.out.println("CallAble");
        return null;
    }
}
