package com.example.Service;

import com.example.Dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * author ye
 * createDate 2022/3/16  12:38
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    @Transactional
    public void insert(){
        userDao.insert();
        System.out.println("插入成功");
        int i = 1/0;
    }
}
