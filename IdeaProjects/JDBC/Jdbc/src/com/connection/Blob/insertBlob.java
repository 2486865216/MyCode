package com.connection.Blob;

import com.connection.JdbcUtil;
import org.junit.Test;

import java.io.*;
import java.sql.*;

public class insertBlob {
    @Test
    public void test() throws IOException, SQLException {
        Connection connection = JdbcUtil.getConnection("user");
        String sql = "insert into test2(user,photo) values(?,?)";
        PreparedStatement p = connection.prepareStatement(sql);
        FileInputStream fileInputStream = new FileInputStream(new File("src/girl.jpg"));
        p.setObject(1, "1");
        p.setBlob(2, fileInputStream);
        p.execute();
        JdbcUtil.closeRourse(connection, p);
    }

    @Test
    public void test2() throws IOException, SQLException {
        Connection connection = JdbcUtil.getConnection("user");
        String sql = "select user,photo from test2 where user='1'";
        PreparedStatement p = connection.prepareStatement(sql);
        ResultSet resultSet = p.executeQuery();
        while (resultSet.next()) {
            String user = resultSet.getString("user");
            Blob blob = resultSet.getBlob("photo");
            InputStream is = blob.getBinaryStream();
            FileOutputStream out = new FileOutputStream("beauyiful.jpg");
            byte[] b = new byte[1024];
            int len;
            while ((len = is.read(b)) != -1) {
                out.write(b, 0, len);
            }

            out.flush();
            out.close();
            is.close();
            blob.free();
        }
        JdbcUtil.closeRourse(connection, p);
    }
}
