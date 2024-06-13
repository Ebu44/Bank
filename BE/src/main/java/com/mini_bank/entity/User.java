package com.mini_bank.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.UUID;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String email;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @Nullable
    private List<Account> accounts;
}
