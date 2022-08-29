package test.utils;

import org.junit.Test;

import java.sql.Connection;

public class test {
    @Test
    public void test1(){
        Connection connection = jdbcutils.getConnection();
        System.out.println(connection);
        jdbcutils.close(connection);
    }
}
