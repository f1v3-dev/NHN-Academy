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
public class ConsistencyMain {

    static BankService bankService = new BankServiceImpl(new AccountRepositoryImpl());

    public static void main(String[] args) throws SQLException {

        Connection connection = DbUtils.getDataSource().getConnection();
        connection.setAutoCommit(false);
        init(connection);


        try {
            Account account1 = new Account(8000L, "nhn아카데미", 100_000L);
            bankService.createAccount(connection, account1);
            log.debug("account1 - 8000 계좌 생성");

            Account account2 = new Account(8000L, "nhn아카데미", 100_000L);
            bankService.createAccount(connection, account2);
            log.debug("account2 - 8000 계좌 생성 시도");

        } catch (Exception e) {
            log.debug("error = {}", e.getMessage());
            log.debug("account1, 8000 계좌가 생성되어 있으므로 rollback 처리, account1, account2 동일한 Transaction에 속하므로 모두 rollback");

            connection.rollback();
        }
    }

    public static void init(Connection connection) {
        // 8000 계좌가 있다면 삭제
        try {
            if (bankService.isExistAccount(connection, 8000L)) {
                bankService.dropAccount(connection, 8000L);
            }
        } catch (Exception e) {
            log.debug("init = {}", e.getMessage());
        }
    }
}
