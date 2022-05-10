package com.qiqi.Proxy.CglibProxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * author ye
 * createDate 2022/2/7  11:20
 */
public class ProxyFactory implements MethodInterceptor {
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    //返回target的代理对象
    public Object getInstance(){
        //1,创建一个工具类
        Enhancer enhancer = new Enhancer();
        //2,设置父类
        enhancer.setSuperclass(target.getClass());
        //3,设置回调函数
        enhancer.setCallback(this);
        //4,创建子类对象，即代理对象
        return enhancer.create();
    }

    /**
     * 重写intercept，会调用目标对象的方法
     * @param o
     * @param method
     * @param objects
     * @param methodProxy
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("代理开始");
        Object invoke = method.invoke(target, objects);
        System.out.println("代理提交");
        return invoke;
    }
}
