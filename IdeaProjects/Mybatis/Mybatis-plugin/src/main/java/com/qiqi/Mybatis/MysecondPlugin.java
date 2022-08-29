package com.qiqi.Mybatis;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;

import java.util.Properties;

@Intercepts({
        @Signature(type = StatementHandler.class, method = "parameterize", args = java.sql.Statement.class)
})
public class MysecondPlugin implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("MybatisSecondPlugin--Interceptor"+invocation.getMethod());
        Object proceed = invocation.proceed();
        //执行目标方法
        return proceed;
    }

    @Override
    public Object plugin(Object target) {
        System.out.println("MybatisPluginSecond--包装的对象"+target);
        //包装目标对象：为目标对象创建一个代理对象
        Object warp = Plugin.wrap(target, this);
        return warp;
    }

    @Override
    public void setProperties(Properties properties) {
        //将插件注册时的属性设置进来
        System.out.println("Second插件的配置信息:"+properties);
        Interceptor.super.setProperties(properties);
    }
}
