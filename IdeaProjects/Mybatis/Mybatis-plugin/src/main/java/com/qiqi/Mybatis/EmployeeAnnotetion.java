package com.qiqi.Mybatis;

import org.apache.ibatis.annotations.Select;

public interface EmployeeAnnotetion {
    @Select("select * from employee where id = #{id}")
    public Employee getEmployeeById(Integer id);
}
