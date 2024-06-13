package com.mini_bank.service;

import com.mini_bank.entity.User;
import com.mini_bank.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public Optional<User> findUserByEmail(String email)
    {
        return userRepository.findUserByEmail(email);
    }
}
