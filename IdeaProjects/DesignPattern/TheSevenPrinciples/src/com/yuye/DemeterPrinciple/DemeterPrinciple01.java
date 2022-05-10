package com.yuye.DemeterPrinciple;

import java.net.CookieHandler;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * author ye
 * createDate 2022/1/25  14:28
 */
public class DemeterPrinciple01 {
    public static void main(String[] args) {
        SchoolManager schoolManager = new SchoolManager();
        schoolManager.printAllEmployees(new CollegeManager());
    }
}
//学校员工
class Employee{
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
//学院员工
class CollegeEmployee{
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
//管理学院员工
class CollegeManager{
    //返回学院员工
    public List<CollegeEmployee> getAllCollegeEmploy(){
        List<CollegeEmployee> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            CollegeEmployee collegeEmployee = new CollegeEmployee();
            collegeEmployee.setId(i);
            list.add(collegeEmployee);
        }
        return list;
    }
}
//管理学校员工
class SchoolManager{
    //返回学校员工
    public List<Employee> getAllEmploy(){
        List<Employee> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Employee Employee = new Employee();
            Employee.setId(i);
            list.add(Employee);
        }
        return list;
    }
    void printAllEmployees(CollegeManager collegeManager){
        List<CollegeEmployee> allCollegeEmploy = collegeManager.getAllCollegeEmploy();
        System.out.println("学院员工");
        for (CollegeEmployee collegeEmployee : allCollegeEmploy) {
            System.out.println(collegeEmployee.getId());
        }
        List<Employee> allEmploy = getAllEmploy();
        System.out.println("学校员工");
        for (Employee employee : allEmploy) {
            System.out.println(employee.getId());
        }
    }
}
