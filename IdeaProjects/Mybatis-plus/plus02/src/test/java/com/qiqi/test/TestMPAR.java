package com.qiqi.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qiqi.mybatisplus.Employee;
import com.qiqi.mybatisplus.EmployeeMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TestMPAR {
    ApplicationContext context =
            new ClassPathXmlApplicationContext("application.xml");

    /**
     * AR 插入
     */
    @Test
    public void testInsert(){
        Employee employee = new Employee(null,"AR","AR@qq",1,250);
        boolean insert = employee.insert();
        System.out.println(insert);
    }

    /**
     * AR 修改
     */
    @Test
    public void testUpdata(){
        Employee employee = new Employee(5,"AR","AR@qq",1,250);
        boolean insert = employee.updateById();
        System.out.println(insert);
    }

    /**
     * AR 查询
     */
    @Test
    public void testSelect(){
        Employee employee = new Employee(4,"AR","AR@qq",1,250);
        //Employee insert = employee.selectById(5);
        //Employee insert = employee.selectById();
        //System.out.println(insert);

        List<Employee> employee1 = employee.selectAll();
        for (Employee employee2 : employee1) {
            System.out.println(employee2);
        }

        //List<Employee> employeeList = employee.selectList(new QueryWrapper<Employee>()
        //        .like("lastname","h"));
        //
        //for (Employee employee2 : employeeList) {
        //    System.out.println(employee2);
        //}

        long gender = employee.selectCount(new QueryWrapper<Employee>().eq("gender", 0));
        System.out.println(gender);
    }

    /**
     * AR 删除
     */
    @Test
    public void testDelete(){
        Employee employee = new Employee(3,"AR","AR@qq",1,250);
        //boolean insert = employee.deleteById(2);
        boolean insert = employee.deleteById();
        boolean id = employee.delete(new QueryWrapper<Employee>().eq("id", 20));

        System.out.println(insert);
        System.out.println(id);
    }

    /**
     * AR 分页复杂操作
     */
    @Test
    public void testPage(){
        Employee employee = new Employee();
        Page<Employee> employeePage = employee.selectPage(new Page<Employee>(2, 2), new QueryWrapper<Employee>());
        List<Employee> employees = employeePage.getRecords();
        for (Employee employee1 : employees) {
            System.out.println(employee1);
        }
    }
}
