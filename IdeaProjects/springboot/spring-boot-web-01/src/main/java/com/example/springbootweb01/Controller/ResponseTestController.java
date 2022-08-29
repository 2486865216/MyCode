package com.example.springbootweb01.Controller;

import com.example.springbootweb01.Bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
public class ResponseTestController {
    /**
     * 1、浏览器发请求直接返回xml   [application/xml] jacksonXmlConverter
     * 2、如果是ajax请求 返回 json [application/json] jacksonJsonConverter
     * 3、如果硅谷app发请求，返回自定义协议数据 [application/x-guigu] xxxConverter
     *          属性值1;属性值2;
     * 步骤：
     * 1、添加自定义的MessageConverter进系统底层
     * 2、系统底层就会统计出所有MessageConverter能操作哪些类型
     * 3、客户端内容协商[guigu--->guigu]
     * @return
     */
    @ResponseBody
    @GetMapping("/test/User")
    public User testUser(){
        User user = new User();
        user.setUsername("helloword");
        user.setAge(18);
        user.setBirth(new Date("2022/01/18"));
        return user;
    }
}
