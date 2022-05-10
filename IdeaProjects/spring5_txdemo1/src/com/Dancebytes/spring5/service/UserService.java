package com.Dancebytes.spring5.service;

import com.Dancebytes.spring5.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
//@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class UserService {

    //注入dao
    @Autowired
    private UserDao userDao;
    public void accontMoney(){
        userDao.reduceMoney();

        System.out.println(12/0);

        userDao.addMoney();
    }
}
