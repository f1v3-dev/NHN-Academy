package com.nhnacademy.jdbc.bank.repository.impl;

import com.nhnacademy.jdbc.bank.domain.Account;
import com.nhnacademy.jdbc.bank.repository.AccountRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AccountRepositoryImpl implements AccountRepository {

    public Optional<Account> findByAccountNumber(Connection connection, long accountNumber) {

        String sql = "SELECT * FROM jdbc_account WHERE account_number = ?";

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setLong(1, accountNumber);

            ResultSet resultSet = psmt.executeQuery();
            if (resultSet.next()) {
                Account account = new Account(
                        resultSet.getLong("account_number"),
                        resultSet.getString("name"),
                        resultSet.getLong("balance")
                );

                return Optional.of(account);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }

    @Override
    public int save(Connection connection, Account account) {

        String sql = "INSERT INTO jdbc_account VALUES(?, ?, ?)";

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {

            psmt.setLong(1, account.getAccountNumber());
            psmt.setString(2, account.getName());
            psmt.setLong(3, account.getBalance());
            return psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int countByAccountNumber(Connection connection, long accountNumber) {
        int count = 0;


        String sql = "SELECT count(*) FROM jdbc_account WHERE account_number = ?";

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setLong(1, accountNumber);

            ResultSet resultSet = psmt.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return count;
    }

    @Override
    public int deposit(Connection connection, long accountNumber, long amount) {

        String sql = "UPDATE jdbc_account SET balance = balance + ? WHERE account_number = ?";

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {

            psmt.setLong(1, amount);
            psmt.setLong(2, accountNumber);

            return psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int withdraw(Connection connection, long accountNumber, long amount) {

        String sql = "UPDATE jdbc_account SET balance = balance - ? WHERE account_number = ?";

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {

            psmt.setLong(1, amount);
            psmt.setLong(2, accountNumber);

            return psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deleteByAccountNumber(Connection connection, long accountNumber) {

        String sql = "DELETE FROM jdbc_account WHERE account_number = ?";

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {

            psmt.setLong(1, accountNumber);

            return psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
