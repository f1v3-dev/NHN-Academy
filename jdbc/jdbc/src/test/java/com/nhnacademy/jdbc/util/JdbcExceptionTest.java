package com.nhnacademy.jdbc.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Slf4j
public class JdbcExceptionTest {

    static Connection connection;

    @BeforeAll
    static void setUp() {
        connection = DbUtils.getConnection();
    }

    @Test
    @DisplayName("sqlExceptionTest")
    void insert_throw_sqlException() {
        String sql = "insert into jdbc_students (id, name, gender, age) values(100, '승조', 'M', '25')";

        SQLException sqlException = Assertions.assertThrows(SQLException.class, () -> {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        });

        log.info("error Code = {}", sqlException.getErrorCode());
        log.info("message = {}", sqlException.getMessage());
        log.info("sql State = {}", sqlException.getSQLState());
    }

    @AfterAll
    static void release() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
