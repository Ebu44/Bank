package com.mini_bank.controller;// TransactionController.java
import com.mini_bank.entity.Transaction;
import com.mini_bank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/api/transactions")
@CrossOrigin(origins = "http://localhost:3000")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/transfer")
    public Transaction transferMoney(@RequestParam Long fromAccountId, @RequestParam Long toAccountId, @RequestParam BigDecimal amount) {
        return transactionService.transferMoney(fromAccountId, toAccountId, amount);
    }

    @GetMapping("/account/{accountId}")
    public List<Transaction> getTransactionHistory(@PathVariable Long accountId) {
        return transactionService.getTransactionHistory(accountId);
    }
}