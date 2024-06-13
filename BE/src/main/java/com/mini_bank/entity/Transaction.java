package com.mini_bank.entity;

import com.mini_bank.util.TransactionStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Account from;

    @OneToOne
    private Account to;

    private BigDecimal amount;

    private LocalDateTime transactionDate;

    private TransactionStatus Status;
}