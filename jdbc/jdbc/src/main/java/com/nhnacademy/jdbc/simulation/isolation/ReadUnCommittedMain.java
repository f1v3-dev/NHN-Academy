package com.nhnacademy.jdbc.simulation.isolation;

import com.nhnacademy.jdbc.bank.domain.Account;
import com.nhnacademy.jdbc.bank.repository.impl.AccountRepositoryImpl;
import com.nhnacademy.jdbc.bank.service.BankService;
import com.nhnacademy.jdbc.bank.service.impl.BankServiceImpl;
import com.nhnacademy.jdbc.util.DbUtils;
import java.sql.Connection;
import java.sql.SQLException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReadUnCommittedMain {

    static BankService bankService = new BankServiceImpl(new AccountRepositoryImpl());

    public static void main(String[] args) throws SQLException, InterruptedException {

        init();
        Thread.sleep(1000);

        Connection connection1 = DbUtils.getDataSource().getConnection();
        connection1.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
        connection1.setAutoCommit(false);

        Connection connection2 = DbUtils.getDataSource().getConnection();
        connection2.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
        connection2.setAutoCommit(false);

        long accountNumber = 10000L;
        Account accountA = bankService.getAccount(connection1, accountNumber);
        Account accountB = bankService.getAccount(connection2, accountNumber);

        log.debug("=====================================");
        log.debug("accountA = {}", accountA);
        log.debug("accountB = {}", accountB);
        log.debug("=====================================");

        bankService.depositAccount(connection1, accountNumber, 50_000);


        accountA = bankService.getAccount(connection1, accountNumber);
        accountB = bankService.getAccount(connection2, accountNumber);

        log.debug("=====================================");
        log.debug("accountA = {}", accountA);
        log.debug("accountB = {}", accountB);
        log.debug("=====================================");

        connection1.commit();
        connection2.commit();
        connection1.close();
        connection1.close();
    }

    private static void init() throws SQLException {
        Connection connection = DbUtils.getDataSource().getConnection();
        connection.setAutoCommit(false);
        Account account = new Account(10000L, "nhn아카데미-10000", 100_000L);

        if (bankService.isExistAccount(connection, account.getAccountNumber())) {
            bankService.dropAccount(connection, account.getAccountNumber());
        }

        bankService.createAccount(connection, account);
        connection.commit();
    }
}
