package com.qiqi.mybatis;

import java.io.Serializable;
import java.util.List;

public class Department implements Serializable {
    private Integer id;
    private String DepartmentName;
    private List<Employee> emps;

    public Department() {
    }

    public Department(Integer id, String departmentName) {
        this.id = id;
        DepartmentName = departmentName;
    }

    public Department(Integer id) {
        this.id = id;
    }

    public void setEmps(List<Employee> emps) {
        this.emps = emps;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDepartmentName(String departmentName) {
        DepartmentName = departmentName;
    }

    public Integer getId() {
        return id;
    }

    public String getDepartmentName() {
        return DepartmentName;
    }

    public List<Employee> getEmps() {
        return emps;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", DepartmentName='" + DepartmentName + '\'' +
                '}';
    }
}
