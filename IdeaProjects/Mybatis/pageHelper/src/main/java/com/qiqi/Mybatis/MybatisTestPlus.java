package com.qiqi.Mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisTestPlus {
    @Test
    public void test1() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperPlus mapperPlus = openSession.getMapper(EmployeeMapperPlus.class);
            //Employee employeeById = mapperPlus.getEmployeeById(1);
            //System.out.println(employeeById);

            //Employee employeeAnddept = mapperPlus.getEmployeeAnddept(1);
            //System.out.println(employeeAnddept);
            //System.out.println(employeeAnddept.getDept());
            //
            //DepartmentMapper departmentMapper = openSession.getMapper(DepartmentMapper.class);
            //Department deptById = departmentMapper.getDeptById(1);
            //System.out.println(deptById);

            Employee employee = mapperPlus.getEmployByIdStep(1);
            System.out.println(employee.getLastname());
            System.out.println(employee.getDept());

            //List<Employee> empsByDeptIdDept = mapperPlus.getEmpsByDeptIdDept(6);
            //for (Employee employee:empsByDeptIdDept){
            //    System.out.println(employee);
            //}
        }finally {
            openSession.close();
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
            DepartmentMapper departmentMapper = session.getMapper(DepartmentMapper.class);
            //Department department = departmentMapper.getDeptByIdPlus(1);
            //System.out.println(department);
            //System.out.println(department.getEmps());
            Department department = departmentMapper.getDeptByIdStep(1);
            System.out.println(department);
            System.out.println(department.getEmps());


        }finally {
            session.close();
        }
    }
}
