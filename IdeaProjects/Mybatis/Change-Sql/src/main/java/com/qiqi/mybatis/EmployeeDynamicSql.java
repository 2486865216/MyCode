package com.qiqi.mybatis;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeDynamicSql {
    public List<Employee> getEmpByConditionIf(Employee employee);

    public List<Employee> getEmpByConditionTrim(Employee employee);

    public List<Employee> getEmpByConditionChoose(Employee employee);

    public void updateEmp(Employee employee);

    public List<Employee> getEmpByConditionForeach(@Param("ids") List<Integer> ids);

    public void addEmp(@Param("emps") List<Employee> e);

    public List<Employee> getEmpTestInneerParameter(Employee employee);
}
