package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/LoginTest";
    private static String username = "root";
    private static String password = "zzq123456";
    public static Connection getConnection() {
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, username,
                    password);
            return conn;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
