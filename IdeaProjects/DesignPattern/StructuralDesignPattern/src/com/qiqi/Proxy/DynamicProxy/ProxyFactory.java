package com.qiqi.Proxy.DynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * author ye
 * createDate 2022/2/7  10:44
 */
public class ProxyFactory {
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    /**
     * 给目标对象生成一个代理对象
     * ava.lang.ClassLoader loader, 指定当前目标对象使用的类加载器，获取加载器的方法固定
     * java.lang.Class<?>[] interface, 目标对象实现的接口类型，使用泛型方法确认类型
     * java.lang.reflect.InvocationHandler h, 事情处理，执行目标对象的方法时，
     *                              会触发事件处理器方法，会把当前执行的目标对象方法作为参数传入
     * @return
     */
    public Object getProxyInstance(){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("JDK代理开始");
                        //反射机制调用目标对象方法
                        Object invoke = method.invoke(target, args);
                        System.out.println("JDK代理提交");
                        return invoke;
                    }
                });
    }
}
