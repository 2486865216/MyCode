package com.qiqi.spring.controller.EmployeeController;

import com.qiqi.spring.controller.bean.Employee;
import com.qiqi.spring.controller.dao.Employeedao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    private Employeedao employeedao;

    @GetMapping("/employee")
    public String getAllEmployee(Model model){
        Collection<Employee> employeelist = employeedao.getAll();
        model.addAttribute("employeelist",employeelist);
        return "employee_list";
    }

    @RequestMapping(value = "/employee/{id}",method = RequestMethod.DELETE)
    public String deleteEmployee(@PathVariable("id") int id){
        employeedao.delect(id);
        return "redirect:/employee";
    }
    @PostMapping("/employee")
    public String addEmployee(Employee employee){
        employeedao.save(employee);
        return "redirect:/employee";
    }
    @RequestMapping(value = "/employee/{id}",method = RequestMethod.GET)
    public String getEmployeeById(@PathVariable("id") int id, Model model){
        Employee employee = employeedao.get(id);
        model.addAttribute("employee",employee);
        return "employee_update";
    }
    @PutMapping("/employee")
    public String updateEmployee(Employee employee){
        employeedao.save(employee);
        return "redirect:/employee";
    }
}
