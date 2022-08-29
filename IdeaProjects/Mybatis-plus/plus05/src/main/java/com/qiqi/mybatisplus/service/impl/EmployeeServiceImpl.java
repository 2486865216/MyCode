package com.qiqi.mybatisplus.service.impl;

import com.qiqi.mybatisplus.beans.Employee;
import com.qiqi.mybatisplus.mapperplugin.EmployeeMapper;
import com.qiqi.mybatisplus.service.IEmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangyuye
 * @since 2021-12-24
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

}
