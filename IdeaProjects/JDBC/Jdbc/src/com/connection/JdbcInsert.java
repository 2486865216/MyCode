package com.connection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcInsert {
    public static void Inster() throws IOException, SQLException {
        String sql = "insert into test1(user,password) values(59746,1654)";
        Connection connection = JdbcUtil.getConnection("user");
        PreparedStatement pr = connection.prepareStatement(sql);
        pr.execute();
        connection.close();
        pr.close();
    }
}
