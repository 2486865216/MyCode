package com.qiqi.Mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class MybatisTest {
    /**
     * 1.根据xml配置文件（全局配置文件）创建一个SqlSessionFactory对象
     * @throws IOException
     */
    @Test
    public void test() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            //2.获取SqlSession实例，能直接执行已经映射的sql语句
            Object selectOne = openSession.selectOne("com.qiqi.Mybatis.EmployeeMapping.getEmpById", 1);
            System.out.println(selectOne);
        }finally {
            openSession.close();
        }
    }
    @Test
    public void test2() throws IOException {
        //1.获取SqlSessionFactory对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);
        //2.获取获取sqlSession对象
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            //3.获取接口的实现类对象
            //Mybatis会为接口创建一个代理对象，代理对象去执行增删改查
            EmployeeMapping employeeMapping = openSession.getMapper(EmployeeMapping.class);
            Employee employee = employeeMapping.getEmpById(1);
            System.out.println(employeeMapping.getClass());
            System.out.println(employee);
        }finally {
            openSession.close();
        }
    }
    @Test
    public void tset3() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeAnnotetion employeeAnnotetion = openSession.getMapper(EmployeeAnnotetion.class);
            Employee employee = employeeAnnotetion.getEmployeeById(1);
            System.out.println(employee);
        }finally {
            openSession.close();
        }
    }
    /*
    在四大对象创建的时候
        1，不是直接返回而是
            interceptorChain.pluginAll(parameterHandler)
        2，获取到所有Interceptor(拦截器)(插件需要实现的接口)
            调用interceptor。plugin(target):返回target包装后的对象

        public Object pluginAll(Object target) {
            Interceptor interceptor;
            for(Iterator var2 = this.interceptors.iterator(); var2.hasNext(); target = interceptor.plugin(target)) {
                interceptor = (Interceptor)var2.next();
            }

            return target;
        }
        3,插件机制，我们可以使用插件为目标对象创建一个代理对象：AOP(面向切面)
            我们的插件可以为四大对象创建出代理对象
            代理对象就可以拦截到四大对象的每一个执行
    * */
    /*
    * 编写插件
    *   1,编写插件实现类
    *   2，使用@Interceptor注解完成插件签名
    *   3,将写好的文件添加到全局配置文件中
    * */
    @Test
    public void tset4(){

    }
}
