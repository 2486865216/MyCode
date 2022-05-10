package com.qiqi.Proxy.StaticProxy;

/**
 * author ye
 * createDate 2022/2/7  10:32
 */
public class Client {
    public static void main(String[] args) {
        //创建目标对象（被代理对象）
        ITeacherDao target = new TeacherDao();

        //创建代理对象
        ITeacherDaoProxy iTeacherDaoProxy = new ITeacherDaoProxy(target);

        iTeacherDaoProxy.teach();
    }
}
