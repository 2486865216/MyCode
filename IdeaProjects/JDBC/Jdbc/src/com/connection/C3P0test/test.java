package com.connection.C3P0test;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;
import org.junit.Test;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public class test {
    @Test
    public void test1() throws PropertyVetoException, SQLException {
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        cpds.setDriverClass("com.mysql.jdbc.Driver");
        cpds.setJdbcUrl("jdbc:mysql://localhost/user?serveTimezone=UTC");
        cpds.setUser("root");
        cpds.setPassword("123456");
//        连接池中的连接数
        cpds.setInitialPoolSize(10);
        Connection c = cpds.getConnection();

        System.out.println(c);
//      销毁
        DataSources.destroy(cpds);
    }

    @Test
    public void test2() throws SQLException {
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        Connection connection = cpds.getConnection();
        System.out.println(connection);
    }
}
