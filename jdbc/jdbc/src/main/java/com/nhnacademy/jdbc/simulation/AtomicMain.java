package com.nhnacademy.jdbc.simulation;

import com.nhnacademy.jdbc.bank.domain.Account;
import com.nhnacademy.jdbc.bank.repository.impl.AccountRepositoryImpl;
import com.nhnacademy.jdbc.bank.service.BankService;
import com.nhnacademy.jdbc.bank.service.impl.BankServiceImpl;
import com.nhnacademy.jdbc.util.DbUtils;
import java.sql.Connection;
import java.sql.SQLException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AtomicMain {

    public static void main(String[] args) throws SQLException {

        BankService bankService = new BankServiceImpl(new AccountRepositoryImpl());

        Connection connection = DbUtils.getDataSource().getConnection();

        connection.setAutoCommit(false);
        connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

        bankService.createAccount(connection, new Account(8000L, "nhn아카데미-8000", 100_000L));

        try {
            bankService.withdrawAccount(connection, 8000L, 50_000L);

            bankService.depositAccount(connection, 9000L, 50_000L);
            // -> 9000 계좌는 존재하지 않으므로 AccountNotFountException 발생!

            connection.commit();
        } catch (Exception e) {
            log.debug("withdraw = {}", e.getMessage());

            connection.rollback();
        }

        connection.close();
    }
}
