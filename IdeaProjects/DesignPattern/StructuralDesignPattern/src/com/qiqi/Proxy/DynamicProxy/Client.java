package com.qiqi.Proxy.DynamicProxy;

/**
 * author ye
 * createDate 2022/2/7  10:53
 */
public class Client {
    public static void main(String[] args) {
        //闯将目标对象
        ITeacherDao target = new TeacherDao();

        //给目标对象闯将代理对象
        ITeacherDao proxyInstance = (ITeacherDao) new ProxyFactory(target).getProxyInstance();

        //ProxyInstance:class com.sun.proxy.$Proxy0 内存中动态生成了代理对象
        System.out.println("ProxyInstance:"+proxyInstance.getClass());

        //通过代理对象，调用目标对象的方法
        proxyInstance.teach();
    }
}
