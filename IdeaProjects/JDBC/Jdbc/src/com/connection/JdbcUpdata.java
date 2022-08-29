package com.connection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcUpdata {
    public static void updata(String name, String setname, String setnamevalue, String wname, String wvalue) throws IOException, SQLException {
        PreparedStatement pr = null;
        Connection connection = JdbcUtil.getConnection("user");
        String sql = "update " + name + " set " + setname + "='" + setnamevalue + "' where " + wname + "='" + wvalue + "'";
        pr = connection.prepareStatement(sql);
        pr.execute();
        pr.close();
    }
}
