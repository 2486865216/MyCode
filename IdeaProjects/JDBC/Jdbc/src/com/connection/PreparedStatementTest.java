package com.connection;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class PreparedStatementTest {
    @Test
    public void test01() throws IOException {
        Connection connection = null;
        PreparedStatement pr = null;
        InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream("mysql.properties");
        Properties info = new Properties();
        info.load(in);
        String user = info.getProperty("user");
        String password = info.getProperty("password");
        String url = info.getProperty("url");
        String driverClass = info.getProperty("derverClass");

        try {
            connection = DriverManager.getConnection(url, user, password);

            String sql = "insert into test1(user,password) values(123,1123)";
            pr = connection.prepareStatement(sql);
            pr.execute();
//            preparedStatement.setInt(1,12345);
//            preparedStatement.setInt(2,1564);

//            String sql1 = "select * from test1";
//            pr = connection.prepareStatement(sql1);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            System.out.println(connection);
//            while (resultSet.next()){
//                System.out.println(resultSet.getString(1));
//                System.out.println(resultSet.getString(2));
//            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                pr.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
