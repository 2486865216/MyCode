package com.connection;

import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class insertBig {
    @Test
//    批量插入
    public void test() throws IOException, SQLException {
        Connection connection = JdbcUtil.getConnection("user");
        String sql = "insert into test1(user,password) values('123','456')";
        PreparedStatement p = connection.prepareStatement(sql);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 2000; i++) {
//            攒sql
            p.addBatch();
            if (i % 500 == 0) {
//                执行
                p.executeBatch();
//                清空
                p.clearBatch();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("用时:" + (end - start));
        JdbcUtil.closeRourse(connection, p);
    }
}
