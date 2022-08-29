package com.qiqi.Mybatis;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EmployeeMapping {

    //多条记录封装一个Map：Map<Integer, Employee>：键就是这条记录的主键，值就是记录封装后的JavaBean
    //告诉mmybatis封装这个map的时候使用那个属性作为map的key
    @MapKey("id")
    public Map<Integer, Employee> getEmployeesReturnMaps(String lastname);

    //返回一条记录的Map，key就是列名，值就是对应的值
    public Map<String, Object> getEmployeesMap(Integer id);

    public List<Employee> getEmployeesLists(String lastName);

    public Employee getEmployeeMap(Map<String, Object> map);

    public Employee getEmployee(@Param("id") Integer id, @Param("lastname") String lastName);

    public void addEmp(Employee employee);

    public void updateEmp(Employee employee);

    public void deleteEmp(Integer id);
}
