package com.mini_bank.service;

import com.mini_bank.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findUserByEmail(String email);
}
