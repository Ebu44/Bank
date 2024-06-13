package com.mini_bank.service;

import com.mini_bank.entity.User;
import com.mini_bank.model.LoginDTO;
import com.mini_bank.repository.UserRepository;
import com.mini_bank.security.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.mini_bank.util.DataResult;
import com.mini_bank.util.ErrorDataResult;
import com.mini_bank.util.SuccessDataResult;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JwtTokenService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public DataResult login(LoginDTO request)
    {
        try{
            String userMail = request.getEmail();
            String password = request.getPassword();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userMail, password));
            Optional<User> optUser = userRepository.findUserByEmail(userMail);
            if(optUser.isEmpty()){
                return new ErrorDataResult("User not found");
            }
            User user = optUser.get();

            System.out.println(user.getPassword());
            System.out.println(password);

            if (!passwordEncoder.matches(password, user.getPassword())) {
                return new ErrorDataResult("Invalid password");
            }

            String jwtToken = jwtService.generateToken(user.getEmail());
            Map<String, String> map = new HashMap<>();
            map.put("token", jwtToken);
            map.put("username", user.getEmail());
            return new SuccessDataResult(map, "ok");
        }catch(BadCredentialsException ex){
            throw new BadCredentialsException("Username or password incorrect");
        }
    }

    public DataResult register(User request) {
        String username = request.getUsername();
        String email = request.getEmail();

        if (userService.findUserByEmail(email).isPresent()) {
            return new ErrorDataResult("Email already in use");
        }

        String hashedPassword = passwordEncoder.encode(request.getPassword());

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setPassword(hashedPassword);

        userRepository.save(newUser);

        return new SuccessDataResult("User registered successfully");
    }
}
