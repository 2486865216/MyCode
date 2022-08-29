package com.connection;

import org.junit.Test;

import java.io.IOException;
import java.sql.*;

public class SelectTest {
    @Test
    public void test01() throws SQLException, IOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        connection = JdbcUtil.getConnection("user");
        String sql = "insert into test1(user,password) values(59746,1654)";
        preparedStatement = connection.prepareStatement(sql);

//            JdbcSelect.select("test1");
//            JdbcUpdata.updata("test1","user","123","user","456");
//            JdbcSelect.select("test1");
//            JdbcInsert.Inster();
//            JdbcDelete.Delete("test1","789");
//            JdbcSelect.select("test1");
        JdbcUtil.closeRourse(connection, preparedStatement);
    }
}
