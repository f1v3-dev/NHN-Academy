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

        // TODO #1 connection1 - isolation level : REPEATABLE_READ
        Connection connection1 = DbUtils.getDataSource().getConnection();
        connection1.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
        connection1.setAutoCommit(false);

        // TODO #2 connection2 - isolation level : REPEATABLE_READ
        Connection connection2 = DbUtils.getDataSource().getConnection();
        connection2.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
        connection2.setAutoCommit(false);

        // TODO #3 동일한 시점에 10000 계좌 조회 -> 동일한 데이터 출력
        long accountNumber = 10000L;
        Account accountA = bankService.getAccount(connection1, accountNumber);
        Account accountB = bankService.getAccount(connection2, accountNumber);

        log.debug("==========================");
        log.debug("accountA = {}", accountA);
        log.debug("accountB = {}", accountB);
        log.debug("==========================");

        // TODO #4 connection1 - 새로운 계좌 30000을 생성
        long newAccountNumber = 30000L;
        Account newAccount = new Account(newAccountNumber, "nhn아카데미-30000", 100_000L);

        bankService.createAccount(connection1, newAccount);
        connection1.commit();


        // TODO #5 accountA 조회
        accountA = bankService.getAccount(connection1, newAccountNumber);

        // TODO #6 accountB 조회 -> 서로 다른 트랜잭션으로 조회되지 않음.
        try {
            accountB = bankService.getAccount(connection2, newAccountNumber);
        } catch (Exception e) {
            accountB = null;
            log.debug("accountB = {}", e.getMessage());
        }

        // TODO #7 accountB는 null
        log.debug("================================");
        log.debug("accountA:{}", accountA);
        log.debug("accountB is null :{}", Objects.isNull(accountB));
        log.debug("================================");

        // connection2에서 30000계좌에 5만원 입금, bankService.depositAccount() method 내부에 account 존재하는지 check하는 로직이 있음으로 직접 repository를 통해서 입금 시도.
        log.debug("accountB : 5만원 입금");
        accountRepository.deposit(connection2, newAccountNumber, 50_000L);

        // TODO #8 accountB 조회
        accountB = bankService.getAccount(connection2, newAccountNumber);


        // TODO #9 connection1에서 insert된 계좌는 확인할 수 없었지만, update를 통해서 새로운 스냅샷 생성
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
