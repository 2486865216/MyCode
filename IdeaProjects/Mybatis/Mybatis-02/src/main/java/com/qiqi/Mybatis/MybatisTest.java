package com.qiqi.Mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            Employee employee = employeeMapping.getEmployee(1,"hello");
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

    /**
     * 1.mybatis允许直接定义以下返回值
     *      Integer,Boolean,Long
     * SqlSession openSession = sqlSessionFactory.openSession(true);==>自动提交
     * @throws IOException
     */
    @Test
    public void test4() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //获取到的SqlSession不会自动提交数据
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            Employee employee = new Employee(null,"hello",1,"hello@qq");
            //test insert
            EmployeeMapping mapper = openSession.getMapper(EmployeeMapping.class);
            mapper.addEmp(employee);
            System.out.println(employee.getId());
            //test update
            //EmployeeMapping mapping = openSession.getMapper(EmployeeMapping.class);
            //mapping.updateEmp(new Employee(1,"hello",1,"hello@qq"));

            //test delete
            //EmployeeMapping employeeMapping = openSession.getMapper(EmployeeMapping.class);
            //employeeMapping.deleteEmp(1);
            openSession.commit();
        }finally {
            openSession.close();
        }
    }
    @Test
    public void test5() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //获取到的SqlSession不会自动提交数据
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapping mapper = openSession.getMapper(EmployeeMapping.class);
            //Map<String, Object> map = new HashMap<>();
            //map.put("id",1);
            //map.put("lastName","hello");
            //Employee employeeMap = mapper.getEmployeeMap(map);
            //System.out.println(employeeMap);

            //List<Employee> hello = mapper.getEmployeesLists("hello");
            //for (Employee employee:hello) {
            //    System.out.println(employee);
            //}

            //Map<String, Object> map = mapper.getEmployeesMap(1);
            //System.out.println(map);

            Map<Integer, Employee> employeesReturnMaps = mapper.getEmployeesReturnMaps("hello");
            System.out.println(employeesReturnMaps);
            openSession.commit();
        }finally {
            openSession.close();
        }
    }
}
