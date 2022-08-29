package com.qiqi.Mybatis;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EmployeeMapping {
    public List<Employee> getEmployees();

    public void addEmployee(@Param("emp") Employee employee);
}
