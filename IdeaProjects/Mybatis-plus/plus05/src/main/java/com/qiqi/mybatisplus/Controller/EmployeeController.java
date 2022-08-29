package com.qiqi.mybatisplus.Controller;


import com.qiqi.mybatisplus.mapperplugin.EmployeeMapper;
import com.qiqi.mybatisplus.service.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhangyuye
 * @since 2021-12-24
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeMapper employeeService;

    @Autowired
    private EmployeeServiceImpl employeeServiceImpl;
    public String login(){
        return "login";
    }
}

