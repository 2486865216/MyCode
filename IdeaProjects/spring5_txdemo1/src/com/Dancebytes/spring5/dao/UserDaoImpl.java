package com.Dancebytes.spring5.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public void addMoney() {
        String sql = "update t_shiwu set money=money+? where name=?";
        jdbcTemplate.update(sql,100,"lucy");
    }
    @Override
    public void reduceMoney() {
        String sql = "update t_shiwu set money=money-? where name=?";
        jdbcTemplate.update(sql,100,"mary");
    }
}
