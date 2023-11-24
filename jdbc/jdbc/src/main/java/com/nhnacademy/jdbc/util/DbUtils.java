package com.nhnacademy.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtils {
    public DbUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/nhn_academy_46", "root",
                            "!seungjo5");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}

