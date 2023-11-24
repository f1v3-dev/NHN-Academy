package com.nhnacademy.jdbc.user.repository.impl;

import com.nhnacademy.jdbc.user.domain.User;
import com.nhnacademy.jdbc.user.repository.UserRepository;
import com.nhnacademy.jdbc.util.DbUtils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StatementUserRepository implements UserRepository {

    @Override
    public Optional<User> findByUserIdAndUserPassword(String userId, String userPassword) {

        String sql = String.format("SELECT * FROM jdbc_users WHERE user_id='%s' AND user_password='%s'",
                userId, userPassword);

        try (Connection connection = DbUtils.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                User user = new User(
                        resultSet.getString("user_id"),
                        resultSet.getString("user_name"),
                        resultSet.getString("user_password")
                );

                return Optional.of(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }

    @Override
    public Optional<User> findById(String userId) {

        String sql = String.format("SELECT * FROM jdbc_users WHERE user_id='%s'",
                userId);

        try (Connection connection = DbUtils.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                User user = new User(
                        resultSet.getString("user_id"),
                        resultSet.getString("user_name"),
                        resultSet.getString("user_password")
                );

                return Optional.of(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }

    @Override
    public int save(User user) {

        String sql = String.format("INSERT INTO jdbc_users VALUES('%s', '%s', '%s')",
                user.getUserId(), user.getUserName(), user.getUserPassword());

        try (Connection connection = DbUtils.getConnection();
             Statement statement = connection.createStatement()) {

            int result = statement.executeUpdate(sql);
            log.debug("save = {}", result);
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateUserPasswordByUserId(String userId, String userPassword) {

        String sql = String.format("UPDATE jdbc_users SET user_password='%s' WHERE user_id='%s'",
                userPassword, userId);

        try (Connection connection = DbUtils.getConnection();
             Statement statement = connection.createStatement()) {

            int result = statement.executeUpdate(sql);
            log.debug("update Password = {}", result);
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deleteByUserId(String userId) {

        String sql = String.format("DELETE FROM jdbc_users WHERE user_id='%s'",
                userId);

        try (Connection connection = DbUtils.getConnection();
             Statement statement = connection.createStatement()) {

            int result = statement.executeUpdate(sql);
            log.debug("delete user = {}", result);
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
