package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String USER = "alexandra";
    private static final String PASSWORD = "parola";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}