package com.connection.C3P0test;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class utils {
    public static Connection getconnection() throws SQLException {
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        Connection connection = cpds.getConnection();
        return connection;
    }
}
