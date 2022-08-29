package com.qiqi.Mybatis;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.util.Properties;

/**
 * 完成插件签名：
 *      告诉Mybatis当前插件用来拦截哪个对象的哪个方法
 */
@Intercepts({
        @Signature(type = StatementHandler.class, method = "parameterize", args = java.sql.Statement.class)
})
public class MyfirstPlugin implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("MybatisPlugin--Interceptor"+invocation.getMethod());
        /**
         * 动态的改变sql运行的参数，传入1，查询3
         * 获取StatementHandler==>ParameterHandler==>parameterObject
         * 获取target的元数据
         */
        Object target = invocation.getTarget();
        System.out.println("当前拦截到的对象："+target);
        MetaObject metaObject = SystemMetaObject.forObject(target);
        Object p = metaObject.getValue("parameterHandler.parameterObject");
        System.out.println("sql语句用的参数是:"+p);
        //修改
        metaObject.setValue("parameterHandler.parameterObject",3);
        Object proceed = invocation.proceed();
        //执行目标方法
        return proceed;
    }

    @Override
    public Object plugin(Object target) {
        System.out.println("MybatisPlugin--包装的对象"+target);
        //包装目标对象：为目标对象创建一个代理对象
        Object warp = Plugin.wrap(target, this);
        return warp;
    }

    @Override
    public void setProperties(Properties properties) {
        //将插件注册时的属性设置进来
        System.out.println("插件的配置信息:"+properties);
        Interceptor.super.setProperties(properties);
    }
}
