package com.nhnacademy.jdbc.util;

import java.sql.Connection;
import java.sql.SQLException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Slf4j
class DbUtilsTest {

    private static Connection connection = null;

    @BeforeAll
    static void setUp() {
        log.info("connection open()");
        connection = DbUtils.getConnection();
    }

    @Test
    @DisplayName("connected")
    void connectionTest() {
        Assertions.assertNotNull(connection);
    }

    @Test
    @DisplayName("mysql driver load : success")
    void mysql_driverLoadTest() {
        try {
            Class<?> driver = Class.forName("com.mysql.cj.jdbc.Driver");
            log.info("driver = {}", driver);

            Assertions.assertEquals(driver.getName(), com.mysql.cj.jdbc.Driver.class.getName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterAll
    static void release() {
        log.info("connection close()");
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}