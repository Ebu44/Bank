package com.mini_bank.service;

import com.mini_bank.entity.User;
import com.mini_bank.model.LoginDTO;
import com.mini_bank.util.DataResult;

public interface AuthenticationService {
    DataResult login(LoginDTO request);
    DataResult register(User request);
}
