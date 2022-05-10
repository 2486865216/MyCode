package com.qiqi.Mybatis;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class MybatisTest {
    @Test
    public void test4() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapping mapping = openSession.getMapper(EmployeeMapping.class);
            Page<Object> pages = PageHelper.startPage(1, 5);
            List<Employee> employees = mapping.getEmployees();
            for (Employee e:employees){
                System.out.println(e);
            }
            System.out.println("当前页码："+pages.getPageNum());
            System.out.println("总记录数："+pages.getTotal());
            System.out.println("每页的记录数："+pages.getPageSize());
            System.out.println("总页数："+pages.getPages());
        }finally {
            openSession.close();
        }
    }
    @Test
    public void test() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession openSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        try {
            //批量==》预编译一次==》设置参数10000次==》执行一次
            //非批量 (预编译==》设置参数==》执行)==》10000次
            EmployeeMapping mapping = openSession.getMapper(EmployeeMapping.class);
            for (int i = 0; i < 10000; i++) {
                mapping.addEmployee(new Employee(null,UUID.randomUUID().toString().substring(0,5),1,"yuye@qq"));
            }
        }finally {
            openSession.close();
        }
    }
}
