package com.example.springbootweb02.Controller;

import com.example.springbootweb02.Exception.UserNameTooLong;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class Login {

    @RequestMapping(value = {"/", "/login"})
    public String index(){
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginMessage(String username, HttpServletRequest request){
        HttpSession session = request.getSession();
        if (username != null && !username.isEmpty()){
            session.setAttribute("username", username);
            //System.out.println(10/0);
            if (username.length() > 5){
                throw new UserNameTooLong("hello word");
            }
            return "index";
        }
        return "login";
    }
}
