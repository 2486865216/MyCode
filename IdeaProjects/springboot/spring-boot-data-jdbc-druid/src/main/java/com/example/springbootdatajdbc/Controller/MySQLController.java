package com.example.springbootdatajdbc.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class MySQLController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @ResponseBody
    @RequestMapping("/sql")
    public List<Map<String, Object>> querySql(){
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from test");
        for (Map<String, Object> map : maps) {
            System.out.println(map);
        }
        return maps;
    }
}
