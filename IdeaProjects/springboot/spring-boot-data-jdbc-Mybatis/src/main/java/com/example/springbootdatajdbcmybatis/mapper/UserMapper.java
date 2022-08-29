package com.example.springbootdatajdbcmybatis.mapper;

import com.example.springbootdatajdbcmybatis.Bean.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper{
    public User getUser(Integer id);
}
