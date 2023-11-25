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
public class IsolationMain {
    static final BankService bankService = new BankServiceImpl(new AccountRepositoryImpl());

    public static void main(String[] args) throws SQLException, InterruptedException {
        init();

        Thread.sleep(1000);

        //todo#2 A->B에게 만(원) 송금
        Thread threadA = transferThread();
        threadA.setName("송금-Thread");

        //todo#3 A가 10만(원) 인출 시도 -> A는 송금 후 잔고는 9만원 -> 송금실패-예외발생
        Thread threadB = withdrawThread();
        threadB.setName("인출-Thread");

        threadA.start();
        //todo#6 - Thread.sleep(1000)  주석을 걸면 어떻게 될까요?
        Thread.sleep(1000);
        threadB.start();

        //todo#4 threadA, threadB 모두 실행될 때까지 Main Thread 대기.
        while (!(threadA.getState().equals(Thread.State.TERMINATED) &&
                threadB.getState().equals(Thread.State.TERMINATED))) {
            Thread.yield();
        }

        //todo#5 조회
        Connection connection = DbUtils.getDataSource().getConnection();
        Account accountA = bankService.getAccount(connection, 10000);
        Account accountB = bankService.getAccount(connection, 20000);

        log.debug("=================================");
        log.debug("accountA:{}", accountA);
        log.debug("accountB:{}", accountB);
        log.debug("=================================");

    }

    public static void init() throws SQLException {
        Connection connection = DbUtils.getDataSource().getConnection();
        connection.setAutoCommit(false);

        //todo#1 account (A,B) 잔고 : 10_0000 생성합니다.
        Account accountA = new Account(1L, "nhn아카데미-1", 100_000L);
        Account accountB = new Account(2L, "nhn아카데미-2", 100_000L);

        try {
            if (bankService.isExistAccount(connection, 1L)) {
                bankService.dropAccount(connection, 1L);
            }

            if (bankService.isExistAccount(connection, 2L)) {
                bankService.dropAccount(connection, 2L);
            }

            bankService.createAccount(connection, accountA);
            bankService.createAccount(connection, accountB);

            connection.commit();
        } catch (Exception e) {
            log.info("error = {}", e);
            connection.rollback();
        } finally {
            connection.close();
        }

    }


    public static Thread transferThread() {

        return new Thread(() -> {
            Connection connection = null;

            try {
                connection = DbUtils.getDataSource().getConnection();
                connection.setAutoCommit(false);
                Thread.sleep(1000);

                // 1 -> 2 계좌로 만원 송금
                bankService.transferAmount(connection, 1L, 2L, 10_000L);
                connection.commit();

                log.debug("송금 완료 !");

            } catch (Exception e) {
                log.debug("송금 실패 = {}", e.getMessage());

                try {
                    connection.rollback();
                } catch (SQLException se) {
                    throw new RuntimeException(se);
                }

                throw new RuntimeException(e);
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }


    public static Thread withdrawThread() {

        return new Thread(() -> {
            Connection connection = null;

            try {
                connection = DbUtils.getDataSource().getConnection();
                connection.setAutoCommit(false);

                // 송금이 먼저 실행될 수 있도록 Thread.sleep(1000)
                Thread.sleep(1000);

                // 1 계좌에서 10만원 출금
                bankService.withdrawAccount(connection, 1L, 100_000);
                connection.commit();

                log.debug("A -> 10만원 인출 완료");
            } catch (Exception e) {
                log.debug("출금 오류 = {}", e.getMessage());

                try {
                    connection.close();
                } catch (SQLException se) {
                    throw new RuntimeException(se);
                }

                throw new RuntimeException(e);
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
