package com.connection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcSelect {
    public static void select(String name) throws IOException, SQLException {
        PreparedStatement pr = null;
        Connection connection = JdbcUtil.getConnection("user");
        String sql = "select * from " + name;
        pr = connection.prepareStatement(sql);
        ResultSet resultSet = pr.executeQuery();
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1));
        }
        pr.close();
    }
}
