package com.example.Service;

import com.example.Bean.Department;
import com.example.Dao.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author ye
 * createDate 2022/3/20  8:22
 */
@Service
public class DepartService {
    @Autowired
    DepartmentMapper departmentMapper;
    public List<Department> getAll(){
        return departmentMapper.selectByExample(null);
    }
}
