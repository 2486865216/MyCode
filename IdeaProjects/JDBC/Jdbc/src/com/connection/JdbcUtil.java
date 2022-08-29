package com.connection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtil {
    public static Connection getConnection(String name) throws IOException {
        InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbcutil.properties");
        Properties pro = new Properties();
        Connection connection = null;
        pro.load(in);
        String url = pro.getProperty("url") + "/" + name + "?serveTimezone=UTC";
        String user = pro.getProperty("user");
        String password = pro.getProperty("password");
        String driverClass = pro.getProperty("driverClass");

        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public static void closeRourse(Connection connection, PreparedStatement preparedStatement) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
