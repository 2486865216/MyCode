package com.connection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcDelete {
    public static void Delete(String name, String value) throws IOException, SQLException {
        String sql = "delete from " + name + " where password='" + value + "'";
        Connection connection = JdbcUtil.getConnection("user");
        PreparedStatement pr = connection.prepareStatement(sql);
        pr.execute();
        connection.close();
        pr.close();
    }
}
