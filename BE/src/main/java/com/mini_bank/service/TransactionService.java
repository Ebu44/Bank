package com.mini_bank.service;

import com.mini_bank.entity.Account;
import com.mini_bank.entity.Transaction;
import com.mini_bank.repository.AccountRepository;
import com.mini_bank.repository.TransactionRepository;
import com.mini_bank.util.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    public Transaction transferMoney(Long fromAccountId, Long toAccountId, BigDecimal amount) {
        Account fromAccount = accountRepository.findById(fromAccountId).orElseThrow();
        Account toAccount = accountRepository.findById(toAccountId).orElseThrow();

        if (fromAccount.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient funds");
        }

        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        toAccount.setBalance(toAccount.getBalance().add(amount));
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        Transaction transaction = new Transaction();
        transaction.setFrom(fromAccount);
        transaction.setTo(toAccount);
        transaction.setAmount(amount);
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setStatus(TransactionStatus.SUCCESS);
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getTransactionHistory(Long accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow();
        return transactionRepository.findByFromOrTo(account, account);
    }
}