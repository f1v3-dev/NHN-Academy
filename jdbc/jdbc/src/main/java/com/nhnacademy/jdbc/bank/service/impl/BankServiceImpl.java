package com.nhnacademy.jdbc.bank.service.impl;

import com.nhnacademy.jdbc.bank.domain.Account;
import com.nhnacademy.jdbc.bank.exception.AccountAlreadyExistException;
import com.nhnacademy.jdbc.bank.exception.AccountNotFoundException;
import com.nhnacademy.jdbc.bank.exception.BalanceNotEnoughException;
import com.nhnacademy.jdbc.bank.repository.AccountRepository;
import com.nhnacademy.jdbc.bank.service.BankService;
import java.sql.Connection;
import java.util.Optional;

public class BankServiceImpl implements BankService {

    private final AccountRepository accountRepository;

    public BankServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account getAccount(Connection connection, long accountNumber) {

        Optional<Account> optionalAccount = accountRepository.findByAccountNumber(connection, accountNumber);

        if (optionalAccount.isEmpty()) {
            throw new AccountNotFoundException(accountNumber);
        }

        return optionalAccount.get();
    }

    @Override
    public void createAccount(Connection connection, Account account) {

        if (isExistAccount(connection, account.getAccountNumber())) {
            throw new AccountAlreadyExistException(account.getAccountNumber());
        }

        int result = accountRepository.save(connection, account);
        if (result < 1) {
            throw new RuntimeException();
        }
    }

    @Override
    public boolean depositAccount(Connection connection, long accountNumber, long amount) {

        if (!isExistAccount(connection, accountNumber)) {
            throw new AccountNotFoundException(accountNumber);
        }

        int result = accountRepository.deposit(connection, accountNumber, amount);
        return result > 0;
    }

    @Override
    public boolean withdrawAccount(Connection connection, long accountNumber, long amount) {

        if (!isExistAccount(connection, accountNumber)) {
            throw new AccountNotFoundException(accountNumber);
        }

        Optional<Account> optionalAccount = accountRepository.findByAccountNumber(connection, accountNumber);
        Account account = optionalAccount.get();

        if (!account.isWithdraw(amount)) {
            throw new BalanceNotEnoughException(accountNumber);
        }

        int result = accountRepository.withdraw(connection, accountNumber, amount);
        return result > 0;
    }

    @Override
    public void transferAmount(Connection connection, long accountNumberFrom, long accountNumberTo, long amount) {

        // 1. 계좌가 존재하는지 확인

        // 왜 체크를 두번하지?
        if (!isExistAccount(connection, accountNumberFrom)) {
            throw new AccountNotFoundException(accountNumberFrom);
        }

        if (!isExistAccount(connection, accountNumberTo)) {
            throw new AccountNotFoundException(accountNumberTo);
        }

        // accountNumberFrom - 출금

        // 출금 계좌가 존재하는지 확인
        Optional<Account> optionalFromAccount = accountRepository.findByAccountNumber(connection, accountNumberFrom);
        if (optionalFromAccount.isEmpty()) {
            throw new AccountNotFoundException(accountNumberFrom);
        }

        // 입금 계좌가 존재하는지 확인
        Optional<Account> optionalToAccount = accountRepository.findByAccountNumber(connection, accountNumberTo);
        if (optionalToAccount.isEmpty()) {
            throw new AccountNotFoundException(accountNumberFrom);
        }

        Account fromAccount = optionalFromAccount.get();

        if (!fromAccount.isWithdraw(amount)) {
            throw new BalanceNotEnoughException(accountNumberFrom);
        }

        int withdraw = accountRepository.withdraw(connection, accountNumberFrom, amount);

        if (withdraw < 1) {
            throw new RuntimeException("withdraw error");
        }

        // accountNumberTo - 입금
        int deposit = accountRepository.deposit(connection, accountNumberTo, amount);

        if (deposit < 1) {
            throw new RuntimeException("deposit error");
        }

    }

    @Override
    public boolean isExistAccount(Connection connection, long accountNumber) {

        int result = accountRepository.countByAccountNumber(connection, accountNumber);
        return result > 0;
    }

    @Override
    public void dropAccount(Connection connection, long accountNumber) {

        if (!isExistAccount(connection, accountNumber)) {
            throw new AccountNotFoundException(accountNumber);
        }

        int result = accountRepository.deleteByAccountNumber(connection, accountNumber);

        if (result < 1) {
            throw new RuntimeException();
        }
    }

}