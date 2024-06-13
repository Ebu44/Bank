package com.mini_bank.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDTO {
    @NotBlank(message = "Please enter the email")
    private String email;

    @NotBlank(message = "Please enter the password")
    private String password;
}
