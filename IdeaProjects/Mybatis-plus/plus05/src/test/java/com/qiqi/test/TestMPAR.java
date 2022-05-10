package com.qiqi.test;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qiqi.mybatisplus.beans.Employee;
import com.qiqi.mybatisplus.beans.User;
import com.qiqi.mybatisplus.mapperplugin.EmployeeMapper;
import com.qiqi.mybatisplus.mapperplugin.UserMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;


public class TestMPAR {

    ApplicationContext context = new
            ClassPathXmlApplicationContext("application.xml");
    EmployeeMapper employeeMapper = context.getBean("employeeMapper",EmployeeMapper.class);
    UserMapper userMapper = context.getBean("userMapper", UserMapper.class);

    //测试自定义方法
    @Test
    public void tetsPage(){
        Integer integer = employeeMapper.deleteAll();
        System.out.println(integer);
    }

    //逻辑删除
    @Test
    public void tetsLogic(){
        Integer integer = userMapper.deleteById(3);
    }

    //字段填充
    @Test
    public void tetsAutoField(){
        User user = new User();
        user.setLogic_flag(1);
        userMapper.insert(user);
    }
}
