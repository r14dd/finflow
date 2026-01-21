package com.finflow.wallet.service;

import com.finflow.wallet.repository.AccountRepository;
import com.finflow.wallet.repository.UserRepository;
import com.finflow.wallet.entity.UserEntity;
import com.finflow.wallet.entity.AccountEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;


@Service
public class AccountService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    public AccountService(
            UserRepository userRepository,
            AccountRepository accountRepository
    ) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    @Transactional
    public AccountEntity createAccount(
            String username,
            String currency,
            BigDecimal balance
    ) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        AccountEntity account = new AccountEntity(
                "AZ-" + UUID.randomUUID(),
                balance,
                currency,
                user
        );

        user.addAccount(account);
        return accountRepository.save(account);
    }
}
