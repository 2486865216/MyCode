package com.qiqi.test;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qiqi.mybatisplus.beans.Employee;
import com.qiqi.mybatisplus.mapperplugin.EmployeeMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;


public class TestMPAR {

    ApplicationContext context = new
            ClassPathXmlApplicationContext("application.xml");
    EmployeeMapper employeeMapper = context.getBean("employeeMapper",EmployeeMapper.class);

    @Test
    public void tetsPage(){
        Page<Employee> employeePage = employeeMapper.selectPage(new Page<Employee>(2, 2), null);
        List<Employee> records = employeePage.getRecords();
        for (Employee record : records) {
            System.out.println(record);
        }
    }

    //防全表更新与删除插件
    @Test
    public void testDeleteAll(){
        int delete = employeeMapper.delete(null);
        System.out.println(delete);
    }

    //乐观锁
    @Test
    public void testoptimisticLocker(){
        Employee employee = new Employee(5,"156465","1@1325153qq","1",52);
        employee.setVersion(1);
        employeeMapper.updateById(employee);
    }
}
