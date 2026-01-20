package com.finflow.wallet.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "accounts")
public class AcccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String accountNumber;

    @Column(nullable = false, precision = 19, scale = 4) // 19 to the left or right and 4 after the decimal point
    private BigDecimal balance;

    @Column(nullable = false)
    private String currency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    // constructor
    protected AcccountEntity() {}

    public AcccountEntity
                        (
                        Long id,
                        String accountNumber,
                        BigDecimal balance,
                        String currency,
                        UserEntity user
                        )
                        {
                        this.accountNumber = accountNumber;
                        this.balance = balance;
                        this.currency = currency;
                        this.user = user;
                        }

    public Long getId() {
        return id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public String getCurrency() {
        return currency;
    }

    public UserEntity getUser() {
        return user;
    }
}
