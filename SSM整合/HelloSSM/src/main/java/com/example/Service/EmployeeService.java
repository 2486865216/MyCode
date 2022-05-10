package com.example.Service;

import com.example.Bean.Employee;
import com.example.Bean.EmployeeExample;
import com.example.Dao.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author ye
 * createDate 2022/3/19  8:22
 */
@Service
public class EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    public List<Employee> getAllEmploy(){
        return employeeMapper.selectByExampleWithDepartment(null);
    }

    public void saveEmployee(Employee employee) {
        employeeMapper.insertSelective(employee);
    }

    public Employee getEmp(Integer id){
        return employeeMapper.selectByPrimaryKeyWithDepartment(id);
    }
    public void updateEmployee(Employee employee){
        employeeMapper.updateByPrimaryKeySelective(employee);
    }
    public void deleteEmployee(Integer id){
        employeeMapper.deleteByPrimaryKey(id);
    }
    public void deleteEmployAll(List<Integer> ids){
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andEmpIdIn(ids);
        employeeMapper.deleteByExample(example);
    }
}
