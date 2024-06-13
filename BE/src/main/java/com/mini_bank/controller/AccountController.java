package com.mini_bank.controller;

import com.mini_bank.entity.Account;
import com.mini_bank.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/api/accounts")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account, Authentication authentication) {
        Account createdAccount = accountService.createAccount(account, authentication.getName());
        return ResponseEntity.status(201).body(createdAccount);
    }

    @GetMapping
    public ResponseEntity<List<Account>> searchAccounts(@RequestParam(required = false) String number,
                                                        @RequestParam(required = false) String name,
                                                        Authentication authentication) {
        List<Account> accounts = accountService.searchAccounts(number, name, authentication.getName());
        return ResponseEntity.ok(accounts);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable Long id, @RequestBody Account account, Authentication authentication) {
        Account updatedAccount = accountService.updateAccount(id, account, authentication.getName());
        return ResponseEntity.ok(updatedAccount);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id, Authentication authentication) {
        accountService.deleteAccount(id, authentication.getName());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountDetails(@PathVariable Long id, Authentication authentication) {
        Account account = accountService.getAccountDetails(id, authentication.getName());
        return ResponseEntity.ok(account);
    }
}
