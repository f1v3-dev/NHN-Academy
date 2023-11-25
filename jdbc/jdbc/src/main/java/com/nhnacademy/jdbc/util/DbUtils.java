package com.nhnacademy.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.Duration;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

public class DbUtils {
    public DbUtils() {
        throw new IllegalStateException("Utility class");
    }

    private static final DataSource DATA_SOURCE;

    static {
        BasicDataSource basicDataSource = new BasicDataSource();

        basicDataSource.setUrl("jdbc:mysql://localhost:3306/nhn_academy_46");
        basicDataSource.setUsername("root");
        basicDataSource.setPassword("8134");

        basicDataSource.setInitialSize(5);
        basicDataSource.setMaxTotal(5);
        basicDataSource.setMaxIdle(5);
        basicDataSource.setMinIdle(5);

        basicDataSource.setMaxWait(Duration.ofSeconds(2));
        basicDataSource.setValidationQuery("select 1");
        basicDataSource.setTestOnBorrow(true);

        DATA_SOURCE = basicDataSource;
    }

    public static DataSource getDataSource() {
        return DATA_SOURCE;
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/nhn_academy_46",
                            "root", "8134");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}

