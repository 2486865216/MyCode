package com.connection;

import org.junit.Test;

import java.io.InputStream;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.Connection;

public class Connection1 {
    @Test
    public void test01() throws SQLException {
        Driver driver = new com.mysql.jdbc.Driver();
//        name password properties
        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "123456");
        String url = "jdbc:mysql://localhost:3306/user?serveTimezone=UTC";

        Connection connection = driver.connect(url, info);

        System.out.println(connection);
    }

    //    方法二：对方法一的迭代
    @Test
    public void test02() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
//        反射
        Class c = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) c.newInstance();
        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "123456");
        String url = "jdbc:mysql://localhost:3306/user?serveTimezone=UTC";

        Connection connection = driver.connect(url, info);

        System.out.println(connection);
    }

    //    使用DriverManager替换Driver
    @Test
    public void test03() throws Exception {
        Class c = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) c.newInstance();
//        注册驱动
        DriverManager.registerDriver(driver);
        String user = "root";
        String password = "123456";
        String url = "jdbc:mysql://localhost:3306/user?serveTimezone=UTC";

        Connection connection = DriverManager.getConnection(url, user, password);

        System.out.println(connection);
    }

    @Test
    public void test04() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        String user = "root";
        String password = "123456";
        String url = "jdbc:mysql://localhost:3306/user?serveTimezone=UTC";

        Connection connection = DriverManager.getConnection(url, user, password);

        System.out.println(connection);
    }

    @Test
//    将基本信息放在配置文件中
    public void test05() throws Exception {
        InputStream in = Connection1.class.getClassLoader().getResourceAsStream("mysql.properties");
        Properties info = new Properties();
        info.load(in);
        String user = info.getProperty("user");
        String password = info.getProperty("password");
        String url = info.getProperty("url");
        String driverClass = info.getProperty("derverClass");
        Connection connection = DriverManager.getConnection(url, user, password);

        System.out.println(connection);
    }
}
