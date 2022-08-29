package com.example.Controller;

import com.example.Bean.Department;
import com.example.Bean.Message;
import com.example.Service.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * author ye
 * createDate 2022/3/20  8:22
 */
@Controller
public class DepartmentController {
    @Autowired
    DepartService departService;

    @ResponseBody
    @RequestMapping("/dept")
    public Message deptMessage(){
        List<Department> list = departService.getAll();
        return Message.success().add("dept", list);
    }
}
