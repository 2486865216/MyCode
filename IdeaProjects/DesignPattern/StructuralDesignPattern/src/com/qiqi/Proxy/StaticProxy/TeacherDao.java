package com.qiqi.Proxy.StaticProxy;

/**
 * author ye
 * createDate 2022/2/7  10:29
 */
public class TeacherDao implements ITeacherDao{
    @Override
    public void teach() {
        System.out.println("老师正在上课");
    }
}
