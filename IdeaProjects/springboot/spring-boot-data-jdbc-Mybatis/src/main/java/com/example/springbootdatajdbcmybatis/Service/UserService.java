package com.example.springbootdatajdbcmybatis.Service;

import com.example.springbootdatajdbcmybatis.Bean.User;
import com.example.springbootdatajdbcmybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;
    public User getUser(Integer id){
        return userMapper.getUser(id);
    }

}
