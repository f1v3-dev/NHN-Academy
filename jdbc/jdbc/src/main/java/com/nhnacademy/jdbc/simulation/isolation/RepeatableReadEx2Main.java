package com.nhnacademy.jdbc.simulation.isolation;

import com.nhnacademy.jdbc.bank.domain.Account;
import com.nhnacademy.jdbc.bank.repository.AccountRepository;
import com.nhnacademy.jdbc.bank.repository.impl.AccountRepositoryImpl;
import com.nhnacademy.jdbc.bank.service.BankService;
import com.nhnacademy.jdbc.bank.service.impl.BankServiceImpl;
import com.nhnacademy.jdbc.util.DbUtils;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RepeatableReadEx2Main {

    static AccountRepository accountRepository = new AccountRepositoryImpl();

    static BankService bankService = new BankServiceImpl(accountRepository);

    public static void main(String[] args) throws SQLException, InterruptedException {

        init();
        Thread.sleep(1000);

        Connection connection1 = DbUtils.getDataSource().getConnection();
        connection1.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
        connection1.setAutoCommit(false);

        Connection connection2 = DbUtils.getDataSource().getConnection();
        connection2.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
        connection2.setAutoCommit(false);

        long accountNumber = 10000L;
        Account accountA = bankService.getAccount(connection1, accountNumber);
        Account accountB = bankService.getAccount(connection2, accountNumber);

        log.debug("==========================");
        log.debug("accountA = {}", accountA);
        log.debug("accountB = {}", accountB);
        log.debug("==========================");

        long newAccountNumber = 30000L;
        Account newAccount = new Account(newAccountNumber, "nhn아카데미-30000", 100_000L);

        bankService.createAccount(connection1, newAccount);
        connection1.commit();


        accountA = bankService.getAccount(connection1, newAccountNumber);

        try {
            accountB = bankService.getAccount(connection2, newAccountNumber);
        } catch (Exception e) {
            accountB = null;
            log.debug("accountB = {}", e.getMessage());
        }

        log.debug("================================");
        log.debug("accountA:{}", accountA);
        log.debug("accountB is null :{}", Objects.isNull(accountB));
        log.debug("================================");

        // connection2에서 30000계좌에 5만원 입금, bankService.depositAccount() method 내부에 account 존재하는지 check하는 로직이 있음으로 직접 repository를 통해서 입금 시도.
        log.debug("accountB : 5만원 입금");
        accountRepository.deposit(connection2, newAccountNumber, 50_000L);

        accountB = bankService.getAccount(connection2, newAccountNumber);


        // 즉 update에 대해서는 dirty read 발생
        log.debug("================================");
        log.debug("accountB {}", accountB);
        log.debug("================================");
        connection2.commit();

        connection1.close();
        connection2.close();

    }

    public static void init() throws SQLException {
        Connection connection = DbUtils.getDataSource().getConnection();
        connection.setAutoCommit(false);

        Account account1 = new Account(10_000L, "nhn아카데미-10000", 100_000L);

        if (bankService.isExistAccount(connection, account1.getAccountNumber())) {
            bankService.dropAccount(connection, account1.getAccountNumber());
        }
        bankService.createAccount(connection, account1);

        connection.commit();
        connection.close();
    }
}
