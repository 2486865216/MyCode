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
            //解析文件的每一个信息保存在Configuration中
            //返回包含Configuration的DefaultSqlSessionFactory
            //一个mapperStatements，代表一个增删改查的详细信息
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);
        //2.获取获取sqlSession对象
            //返回包含Configuration和Executor的DefaultSqlSession
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            //3.获取接口的实现类对象
                //getMapper.使用MapperProxyFactory创建一个MapperProxy的代理对象
                //代理对象里面包含了DefaultSqlSession(Executor)
            //Mybatis会为接口创建一个代理对象，代理对象去执行增删改查
            /*5、执行增删改查方法：
                1)、调用DefaultSqlSession的增删改查(Executor)；
                2)、会创建一个StatementHandler对象。
                (同时也会创建出ParameterHandler和ResultSetHandler)
                3)、调用StatementHandler预编译参数以及设置参数值；使用ParameterHandler来给sql设置参数
                4)、调用StatementHandler的增删改查方法；
                5)、ResultSetHandler封装结果

                注意：
                    四大对象创建的时候都有一个interceptorChain.pluginAll(parameterHandler)
            */
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
}
