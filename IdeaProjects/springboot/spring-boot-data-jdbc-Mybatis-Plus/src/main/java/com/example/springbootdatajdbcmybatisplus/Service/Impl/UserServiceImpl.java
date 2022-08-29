package com.example.springbootdatajdbcmybatisplus.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springbootdatajdbcmybatisplus.Bean.User;
import com.example.springbootdatajdbcmybatisplus.Mapper.UserMapper;
import com.example.springbootdatajdbcmybatisplus.Service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
