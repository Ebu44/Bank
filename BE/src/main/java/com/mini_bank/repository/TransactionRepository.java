package com.mini_bank.repository;


import com.mini_bank.entity.Account;
import com.mini_bank.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByFromOrTo(Account from, Account to);
}