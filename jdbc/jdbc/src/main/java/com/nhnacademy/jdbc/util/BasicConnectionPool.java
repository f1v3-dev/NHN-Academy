package com.nhnacademy.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;

public class BasicConnectionPool {
    private final String jdbcUrl;
    private final String username;
    private final String password;
    private final int maximumPoolSize;
    private final Queue<Connection> connections;

    public BasicConnectionPool(String driverClassName, String jdbcUrl, String username, String password,
                               int maximumPoolSize) {

        this.jdbcUrl = jdbcUrl;
        this.username = username;
        this.password = password;
        this.maximumPoolSize = maximumPoolSize;
        this.connections = new LinkedList<>();

        checkDriver(driverClassName);
        initialize();
    }

    private void checkDriver(String driverClassName) {
        try {
            Class.forName(driverClassName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void initialize() {

        try {
            for (int i = 0; i < maximumPoolSize; i++) {
                Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
                connections.offer(connection);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Connection getConnection() throws InterruptedException {

        synchronized (this) {
            while (connections.isEmpty()) {
                wait();
            }
        }
        return connections.poll();
    }

    public void releaseConnection(Connection connection) {

        synchronized (this) {
            connections.offer(connection);
            notifyAll();
        }
    }

    public int getUsedConnectionSize() {
        return this.maximumPoolSize - connections.size();
    }

    public void destroy() throws SQLException {
        for (Connection connection : connections) {
            if (!connection.isClosed()) {
                connection.close();
            }
        }
    }
}
