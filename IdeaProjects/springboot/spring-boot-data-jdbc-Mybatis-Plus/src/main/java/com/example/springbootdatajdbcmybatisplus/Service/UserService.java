package com.example.springbootdatajdbcmybatisplus.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springbootdatajdbcmybatisplus.Bean.User;
import com.example.springbootdatajdbcmybatisplus.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface UserService extends IService<User> {
}
