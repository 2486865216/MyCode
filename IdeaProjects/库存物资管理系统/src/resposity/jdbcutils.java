package resposity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class jdbcutils {
    public static Connection getConnection(String name) throws SQLException {
        String url = "jdbc.mysql://localhost/"+name+"serveTimezone=UTC";
        String user = "root";
        String password = "123456";
        Connection connection = null;
        PreparedStatement p = null;
        connection = DriverManager.getConnection(url,user,password);
        return connection;
    }
}
