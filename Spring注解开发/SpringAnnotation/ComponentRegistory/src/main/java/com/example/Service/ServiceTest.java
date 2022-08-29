package com.example.Service;

import com.example.Dao.DaoTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * author ye
 * createDate 2022/3/14  15:09
 */
@Service
public class ServiceTest {
    //@Qualifier("daoTest")
    @Autowired
    //@Resource(name = "daoTest") //不支持primary
    //@Inject //支持primary
    private DaoTest daoTest;

    public DaoTest getDaoTest() {
        return daoTest;
    }

    @Override
    public String toString() {
        return "ServiceTest{" +
                "daoTest=" + daoTest +
                '}';
    }
}
