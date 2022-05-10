package com.example.mybatisspring.Service;

import com.example.mybatisspring.Employee;
import com.example.mybatisspring.EmployeeMapping;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeMapping employeeMapping;

    //批量操作
    @Autowired
    private SqlSession session;
    //EmployeeMapping mapper = session.getMapper(EmployeeMapping.class);

    public List<Employee> getEmps(){
        return employeeMapping.getEmps();
    }
}
