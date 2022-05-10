package com.qiqi.Proxy.StaticProxy;

/**
 * author ye
 * createDate 2022/2/7  10:29
 *
 * 代理对象,静态代理
 */
public class ITeacherDaoProxy implements ITeacherDao{

    private ITeacherDao target;//目标对象，通过接口聚合

    public ITeacherDaoProxy(ITeacherDao target) {
        this.target = target;
    }

    @Override
    public void teach() {
        System.out.println("开始代理");
        target.teach();
        System.out.println("结束代理");
    }
}
