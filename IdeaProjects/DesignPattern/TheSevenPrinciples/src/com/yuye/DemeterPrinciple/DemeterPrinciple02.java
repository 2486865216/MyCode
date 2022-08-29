package com.yuye.DemeterPrinciple;

import java.util.ArrayList;
import java.util.List;

/**
 * author ye
 * createDate 2022/1/25  14:42
 */
public class DemeterPrinciple02 {
    public static void main(String[] args) {
        SchoolManager1 schoolManager1 = new SchoolManager1();
        schoolManager1.printAllEmployees(new CollegeManager1());
    }
}
//学校员工
class Employee1{
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
//学院员工
class CollegeEmployee1{
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
//管理学院员工
class CollegeManager1{
    //返回学院员工
    public List<CollegeEmployee1> getAllCollegeEmploy(){
        List<CollegeEmployee1> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            CollegeEmployee1 collegeEmployee1 = new CollegeEmployee1();
            collegeEmployee1.setId(i);
            list.add(collegeEmployee1);
        }
        return list;
    }
    public void printAllEmploy(){
        List<CollegeEmployee1> allCollegeEmploy1 = getAllCollegeEmploy();
        System.out.println("学院员工");
        for (CollegeEmployee1 collegeEmployee1 : allCollegeEmploy1) {
            System.out.println(collegeEmployee1.getId());
        }
    }
}
//管理学校员工
class SchoolManager1{
    //返回学校员工
    public List<Employee1> getAllEmploy(){
        List<Employee1> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Employee1 Employee1 = new Employee1();
            Employee1.setId(i);
            list.add(Employee1);
        }
        return list;
    }
    void printAllEmployees(CollegeManager1 collegeManager1){
        collegeManager1.printAllEmploy();
        List<Employee1> allEmploy1 = getAllEmploy();
        System.out.println("学校员工");
        for (Employee1 employee1 : allEmploy1) {
            System.out.println(employee1.getId());
        }
    }
}

