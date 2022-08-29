package com.connection.C3P0test;

import org.junit.Test;

import java.sql.*;

public class select {
    @Test
    public void test() throws SQLException {
        Connection connection = utils.getconnection();
        String sql = "select * from test2";
        PreparedStatement p = connection.prepareStatement(sql);
        ResultSetMetaData r = p.getMetaData();
        ResultSet r1 = p.executeQuery();
        while (r1.next()) {
            System.out.println(r1.getString(1));
            System.out.println(r1.getString(2));
        }
        connection.close();
    }
}
