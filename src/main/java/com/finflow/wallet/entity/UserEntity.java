package com.finflow.wallet.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<AcccountEntity> accounts = new ArrayList<>();

    protected UserEntity() {}

    public UserEntity(String name) {
        this.name = name;
    }

    public void addAccount(AcccountEntity account) {
        accounts.add(account);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<AcccountEntity> getAccounts() {
        return accounts;
    }
}
