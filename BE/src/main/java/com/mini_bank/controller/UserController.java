package com.mini_bank.controller;

import com.mini_bank.entity.User;
import com.mini_bank.model.LoginDTO;
import com.mini_bank.service.AuthenticationService;
import com.mini_bank.util.DataResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final AuthenticationService service;

    @PostMapping("/login")
    public ResponseEntity<DataResult> authenticate(@RequestBody LoginDTO request)
    {
        return ResponseEntity.ok(service.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<DataResult> register(@RequestBody User request)
    {
        return ResponseEntity.ok(service.register(request));
    }
}
