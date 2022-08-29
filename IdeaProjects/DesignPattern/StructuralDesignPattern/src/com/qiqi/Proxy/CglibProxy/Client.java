package com.qiqi.Proxy.CglibProxy;

/**
 * author ye
 * createDate 2022/2/7  11:27
 */
public class Client {
    public static void main(String[] args) {
        TeacherDao target = new TeacherDao();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        TeacherDao instance = (TeacherDao) proxyFactory.getInstance();
        instance.teach();
    }
}
