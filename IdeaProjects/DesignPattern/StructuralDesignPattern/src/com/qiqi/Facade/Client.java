package com.qiqi.Facade;

import org.junit.Test;

/**
 * author ye
 * createDate 2022/2/6  13:39
 */
public class Client {
    @Test
    public void test() {
        HomeFacade homeFacade = new HomeFacade();
        homeFacade.ready();
        homeFacade.play();
        homeFacade.off();
    }
}
