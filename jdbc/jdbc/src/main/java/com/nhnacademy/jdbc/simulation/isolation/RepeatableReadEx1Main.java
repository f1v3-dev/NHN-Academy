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
public class RepeatableReadEx1Main {

    static BankService bankService = new BankServiceImpl(new AccountRepositoryImpl());

    public static void main(String[] args) throws SQLException, InterruptedException {
        init();
        Thread.sleep(1000);

        // TODO #1 : connection1 - isolation level = REPEATABLE_READ
        Connection connection1 = DbUtils.getDataSource().getConnection();
        connection1.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
        connection1.setAutoCommit(false);

        // TODO #2 : connection2 - isolation level = REPEATABLE_READ
        Connection connection2 = DbUtils.getDataSource().getConnection();
        connection2.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
        connection2.setAutoCommit(false);

        long accountNumber = 10_000L;

        // TODO #3 : 동일한 시점에 10000 계좌 조회 -> 둘 다 동일한 값을 출력
        Account accountA = bankService.getAccount(connection1, accountNumber);
        Account accountB = bankService.getAccount(connection2, accountNumber);

        log.debug("================================");
        log.debug("accountA:{}",accountA);
        log.debug("accountB:{}",accountB);
        log.debug("================================");


        // TODO #4 : connection1에서 10000 계좌에 5만원 입금 후 commit
        bankService.depositAccount(connection1, accountNumber, 50_000L);
        connection1.commit();

        log.debug("accountA <--- 50,000원 입금");

        // TODO #5 : connection1 에서는 50,000원이 입금 처리 되었고 (commit 호출로 인해)
        //  connection2에서는 TODO #3에서 조회했던 데이터(스냅샷이 조회됨)를 확인할 수 있음.
        accountA = bankService.getAccount(connection1,accountNumber);
        accountB = bankService.getAccount(connection2,accountNumber);

        log.debug("================================");
        log.debug("accountA:{}",accountA);
        log.debug("accountB:{}",accountB);
        log.debug("================================");

        connection2.commit();

        connection1.close();
        connection2.close();
    }

    private static void init() throws SQLException {
        Connection connection = DbUtils.getDataSource().getConnection();
        connection.setAutoCommit(false);
        Account account = new Account(10_000L, "nhn아카데미-10000", 100_000L);

        if (bankService.isExistAccount(connection, account.getAccountNumber())) {
            bankService.dropAccount(connection, account.getAccountNumber());
        }

        bankService.createAccount(connection, account);
        connection.commit();
    }
}
