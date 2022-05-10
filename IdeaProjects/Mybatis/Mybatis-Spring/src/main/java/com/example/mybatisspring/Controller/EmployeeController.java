package com.example.mybatisspring.Controller;

import com.example.mybatisspring.Employee;
import com.example.mybatisspring.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/emps")
    public String emps(Map<String,Object> map){
        List<Employee> emps = employeeService.getEmps();
        map.put("allEmp",emps);
        return "list";
    }
}
