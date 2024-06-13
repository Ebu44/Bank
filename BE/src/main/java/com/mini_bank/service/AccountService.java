package com.mini_bank.service;

import com.mini_bank.entity.Account;
import com.mini_bank.entity.User;
import com.mini_bank.repository.AccountRepository;
import com.mini_bank.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public Account createAccount(Account account, String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        account.setUsers(user);
        account.setName(username);
        account.setBalance(account.getBalance());
        account.setNumber(account.getNumber());
        return accountRepository.save(account);
    }

    public List<Account> searchAccounts(String number, String name, String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        if (number != null && name != null) {
            return accountRepository.findByUsersAndNumberAndName(user, number, name);
        } else if (number != null) {
            return accountRepository.findByUsersAndNumber(user, number);
        } else if (name != null) {
            return accountRepository.findByUsersAndName(user, name);
        } else {
            return accountRepository.findByUsers(user);
        }
    }

    public Account updateAccount(Long id, Account account, String username) {
        Account existingAccount = accountRepository.findByIdAndUsersUsername(id, username).orElseThrow(() -> new RuntimeException("Account not found"));
        existingAccount.setName(account.getName());
        existingAccount.setBalance(account.getBalance());
        return accountRepository.save(existingAccount);
    }

    public void deleteAccount(Long id, String username) {
        Account account = accountRepository.findByIdAndUsersUsername(id, username).orElseThrow(() -> new RuntimeException("Account not found"));
        accountRepository.delete(account);
    }

    public Account getAccountDetails(Long id, String username) {
        return accountRepository.findByIdAndUsersUsername(id, username).orElseThrow(() -> new RuntimeException("Account not found"));
    }
}
