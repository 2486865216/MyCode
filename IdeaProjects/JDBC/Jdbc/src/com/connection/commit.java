package com.connection;

import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class commit {
    @Test
    public void test() {
        Connection connection = null;
        try {
            connection = JdbcUtil.getConnection("user");
            connection.setAutoCommit(false);
            String sql = "insert into test2(user,photo) value ('15','165')";
            PreparedStatement p = connection.prepareStatement(sql);
            p.execute();
            String sql1 = "insert into test2(user,photo) value ('123','16545')";
            PreparedStatement p1 = connection.prepareStatement(sql1);
            p1.execute();

            connection.commit();
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            System.out.println("successful!");

        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
