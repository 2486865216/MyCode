package com.connection.DBCP;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class test {
    @Test
    public void test1() throws SQLException {
        BasicDataSource dbcp = new BasicDataSource();

        dbcp.setDriverClassName("com.mysql.jdbc.Driver");
        dbcp.setUrl("jdbc:mysql://localhost/user?serveTimezone=UTC");
        dbcp.setUsername("root");
        dbcp.setPassword("123456");

        dbcp.setInitialSize(10);

        Connection connection = dbcp.getConnection();
        System.out.println(connection);
    }

    private static DataSource dbcp = null;

    static {
        Properties pros = new Properties();
        InputStream in = null;
        try {
            in = new FileInputStream(new File("C:\\Users\\烨\\IdeaProjects\\JDBC\\Jdbc\\src\\dbcp.properties"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            pros.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            dbcp = BasicDataSourceFactory.createDataSource(pros);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection test2() throws Exception {
        Connection connection = dbcp.getConnection();
        System.out.println(connection);
        return connection;
    }
}
