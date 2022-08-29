package com.example.springbootweb01.Controller;

import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RequestController {
    @RequestMapping("/goto")
    public String gotoRequests(HttpServletRequest request) {
        request.setAttribute("msg", "Success!");
        request.setAttribute("code", 200);
        return "forward:/success";
    }

    @ResponseBody
    @GetMapping("success")
    public Map<String, Object> SuccessResponse(@RequestAttribute("msg") String msg,
                                               @RequestAttribute("code") Integer code,
                                               HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        String requestMsg = request.getAttribute("msg").toString();
        Integer requestCode =Integer.parseInt(request.getAttribute("code").toString());
        map.put("code",code);
        map.put("msg", msg);
        map.put("requestsMsg", requestMsg);
        map.put("requestCode", requestCode);
        return map;
    }

    @GetMapping("/param")
    public String testParams(Map<String, Object> map,
                             Model model,
                             HttpServletRequest request,
                             HttpServletResponse response){
        map.put("hello","word");
        model.addAttribute("word", "hello");
        request.setAttribute("message", "helloWord");
        Cookie cookie = new Cookie("c1", "v1");
        cookie.setDomain("localhost");
        response.addCookie(cookie);
        return "forward:/paramSuccess";
    }
    @GetMapping("/paramSuccess")
    @ResponseBody
    public Map<String, Object> testParamsSuccess(HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        Object hello = request.getAttribute("hello");
        Object word = request.getAttribute("word");
        Object message = request.getAttribute("message");
        map.put("hello", hello);
        map.put("word", word);
        map.put("message", message);
        return map;
    }
}
