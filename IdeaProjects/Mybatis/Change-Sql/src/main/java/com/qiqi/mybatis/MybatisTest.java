package com.qiqi.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MybatisTest {
    @Test
    public void testIf() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        try {
            EmployeeDynamicSql employeeDynamicSql = session.getMapper(EmployeeDynamicSql.class);
            //Employee employee1 = new Employee(null,"hello",1,"hello@qq");
            //List<Employee> empByConditionIf = employeeDynamicSql.getEmpByConditionIf(employee1);
            //System.out.println(empByConditionIf);

            //测试trim
            //Employee employee2 = new Employee(null,"__l%",null,null);
            //List<Employee> employees = employeeDynamicSql.getEmpByConditionTrim(employee2);
            //System.out.println(employees);

            //测试choose
            /*Employee employee3 = new Employee(null,"__l%",null,null);
            List<Employee> employeeList = employeeDynamicSql.getEmpByConditionChoose(employee3);
            for (Employee employee:employeeList){
                System.out.println(employee);
            }*/

            //测试set
            /*Employee employeeSet = new Employee(1,"admin",null,null);
            employeeDynamicSql.updateEmp(employeeSet);
            session.commit();*/

            //测试foreach
            List<Employee> empByConditionForeach = employeeDynamicSql.getEmpByConditionForeach(Arrays.asList(4, 5, 6));
            for (Employee e:empByConditionForeach){
                System.out.println(e);
            }
        }finally {
            session.close();
        }
    }
    @Test
    public void test2() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        try {
            EmployeeDynamicSql mapper = session.getMapper(EmployeeDynamicSql.class);
            List<Employee> employees = new ArrayList<>();
            employees.add(new Employee(null,"jreey",0,"jreey@qq",new Department(1)));
            employees.add(new Employee(null,"llll",1,"llll@qq",new Department(2)));
            mapper.addEmp(employees);
            session.commit();
        }finally {
            session.close();
        }
    }
    @Test
    public void test3() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        try {
            EmployeeDynamicSql mapper = session.getMapper(EmployeeDynamicSql.class);
            List<Employee> employees = new ArrayList<>();
            employees.add(new Employee(null,"jreey",0,"jreey@qq",new Department(1)));
            List<Employee> empTestInneerParamter = mapper.getEmpTestInneerParameter(new Employee(null,"e",null,null));
            for (Employee e:empTestInneerParamter){
                System.out.println(e);
            }
            session.commit();
        }finally {
            session.close();
        }
    }
}
