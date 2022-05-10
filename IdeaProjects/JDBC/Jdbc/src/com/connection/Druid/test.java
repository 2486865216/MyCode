package com.connection.Druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Date;
import java.util.Properties;

public class test {
    private static DataSource druid = null;

    static {
        Properties pros = new Properties();
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties");
        try {
            pros.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            druid = DruidDataSourceFactory.createDataSource(pros);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void test1() throws Exception {
        Connection connection = druid.getConnection();
        System.out.println(connection);
    }
}
