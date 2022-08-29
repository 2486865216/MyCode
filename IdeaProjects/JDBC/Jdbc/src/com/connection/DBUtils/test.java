package com.connection.DBUtils;

import com.connection.JdbcUtil;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class test {
    @Test
    public void test1() throws IOException, SQLException {
        QueryRunner runner = new QueryRunner();
        Connection connection = JdbcUtil.getConnection("user");
        String sql = "insert into test2(user,photo) values('16481','15648168')";
        int result = runner.update(connection, sql);
        System.out.println(result);
        DbUtils.close(connection);
    }
}
