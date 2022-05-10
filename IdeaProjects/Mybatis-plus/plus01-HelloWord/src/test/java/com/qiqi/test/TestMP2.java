package com.qiqi.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.qiqi.mybatisplus.Employee;
import com.qiqi.mybatisplus.EmployeeMapper;
import com.qiqi.mybatisplus.MybatisPlusConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * 条件构造器
 */
public class TestMP2 {
    ApplicationContext context =
            new ClassPathXmlApplicationContext("application.xml");

    private EmployeeMapper employeeMapper =
            context.getBean("employeeMapper",EmployeeMapper.class);

    @Test
    public void test(){
        Page<Employee> age = employeeMapper.selectPage(new Page<Employee>(1, 1),
                new QueryWrapper<Employee>()
                        .between("age", 15, 50)
                        .eq("gender", 1));
        List<Employee> employees = age.getRecords();
        for (Employee e:employees){
            System.out.println(e);
        }

        //List<Employee> employees = employeeMapper.selectList(
        //        new QueryWrapper<Employee>().eq("lastname", "marry")
        //        .like("email", "@")
        //        .or()
        //        .eq("gender",0));
        //for (Employee employee:employees){
        //    System.out.println(employee);
        //}
    }

    /**
     * update
     */
    @Test
    public void updata(){
        Employee employee = new Employee();
        employee.setLastname("hello word");
        employee.setEmail("java@QQ");
        employee.setGender(0);

        employeeMapper.update(employee, new QueryWrapper<Employee>()
                .eq("lastname","marry").eq("age",17));
    }

    @Test
    public void testDelete(){
        employeeMapper.delete(new QueryWrapper<Employee>().eq("id",37));
    }

    @Test
    public void AscAndDesc(){
        //orderByAsc("id", "name")--->order by id ASC,name ASC
        //orderByDesc("id", "name")--->order by id DESC,name DESC
        List<Employee> employees = employeeMapper.selectList(new QueryWrapper<Employee>()
                .eq("gender", 0)
                .orderByDesc("age"));
        for (Employee e:employees){
            System.out.println(e);
        }
    }
}
