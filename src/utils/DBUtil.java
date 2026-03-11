package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5432/quanlydt?curentSchema=quanlysanpham";
    private static final String USERName = "postgres";
    private static final String PASSWORD = "Vuhao2005@";
    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            try {
                return DriverManager.getConnection(URL, USERName, PASSWORD);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
