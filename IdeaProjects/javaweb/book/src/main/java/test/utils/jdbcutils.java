package test.utils;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class jdbcutils {
    private static DruidDataSource druidDataSource;
    static {
        InputStream in = jdbcutils.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties ps = new Properties();
        try {
            ps.load(in);
            druidDataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(ps);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection(){
        try {
            Connection connection = druidDataSource.getConnection();
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void close(Connection connection){
        if (connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
