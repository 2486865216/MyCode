package com.example.Controller;

import com.example.Bean.Employee;
import com.example.Bean.Message;
import com.example.Service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.AbstractBindingResult;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author ye
 * createDate 2022/3/19  8:23
 */
@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/emp", method = RequestMethod.GET)
    @ResponseBody
    public Message employeeList(@RequestParam(value = "page", defaultValue = "1") Integer page){
        PageHelper.startPage(page, 5);
        List<Employee> list = employeeService.getAllEmploy();
        //使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
        //封装了详细的分页信息，包括有我们查询出来的数据，传入连续显示的页数
        PageInfo pageInfo = new PageInfo(list, 5);
        return Message.success().add("pageInfo", pageInfo);
    }

    @PostMapping("/emp")
    @ResponseBody
    public Message saveEmpolyee(@Valid Employee employee, AbstractBindingResult result){
        //System.out.println(result.hasErrors());
        //System.out.println(employee);
        //    List<FieldError> fieldErrors = result.getFieldErrors();
        //    Map<String, String> errorMessage = new HashMap<>();
        //    for (FieldError fieldError : fieldErrors) {
        //        System.out.println("错误的字段是：" + fieldError.getField());
        //        System.out.println("错误的消息是：" + fieldError.getDefaultMessage());
        //        errorMessage.put(fieldError.getField(), fieldError.getDefaultMessage());
        //    }
        //
        //    return Message.failure().add("errorField", errorMessage);
            employeeService.saveEmployee(employee);
            return Message.success();
    }

    @PutMapping("/emp/{id}")
    @ResponseBody
    public Message getEmp(@PathVariable("id") Integer id){
        Employee emp = employeeService.getEmp(id);
        return Message.success().add("emp", emp);
    }

    @ResponseBody
    @PutMapping("/test")
    public String test(){
        return "test";
    }

    /*我们要能支持直接发送PUT之类的请求还要封装请求体中的数据
         配置上org.springframework.web.filter.FormContentFilter;
        他的作用；将请求体中的数据解析包装成一个map。
        request被重新包装，request,getParameter()被重写，就会从自己封装的map中取数据*/
    @ResponseBody
    @PutMapping("/update")
    public Message updateEmp(Employee employee){
        employeeService.updateEmployee(employee);
        return Message.success();
    }
    @ResponseBody
    @DeleteMapping("/delete/{ids}")
    public Message deleteEmp(@PathVariable("ids") String ids){
        if (ids.contains("-")){
            List<Integer> list = new ArrayList<>();
            for (String s : ids.split("-")) {
                list.add(Integer.parseInt(s));
            }
            employeeService.deleteEmployAll(list);
        }else {
            employeeService.deleteEmployee(Integer.parseInt(ids));
        }
        return Message.success();
    }
}
