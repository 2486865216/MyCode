package resposity;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            Connection connection = jdbcutils.getConnection("user");
            System.out.println(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}