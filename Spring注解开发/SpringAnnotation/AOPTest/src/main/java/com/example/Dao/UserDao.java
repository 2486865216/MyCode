package com.example.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * author ye
 * createDate 2022/3/16  12:38
 */
@Repository
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public void insert(){
        String sql = "insert into `springuser`(name, age) value (?, ?)";
        String substring = UUID.randomUUID().toString().substring(0, 5);
        jdbcTemplate.update(sql, substring, 18);
    }
}
