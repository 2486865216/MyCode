package com.qiqi.Mybatis;

import java.util.List;

public interface EmployeeMapperPlus {
    public Employee getEmployeeById(Integer id);

    public Employee getEmployeeAnddept(Integer id);

    public Employee getEmployByIdStep(Integer id);

    public List<Employee> getEmpsByDeptId(Integer id);

    public List<Employee> getEmpsByDeptIdDept(Integer id);
}
