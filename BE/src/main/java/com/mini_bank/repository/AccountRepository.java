package com.mini_bank.repository;

import com.mini_bank.entity.Account;
import com.mini_bank.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByUsers(User user);
    List<Account> findByUsersAndNumber(User user, String number);
    List<Account> findByUsersAndName(User user, String name);
    List<Account> findByUsersAndNumberAndName(User user, String number, String name);
    Optional<Account> findByIdAndUsersUsername(Long id, String username);
}
