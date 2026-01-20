package com.finflow.wallet.service;

import com.finflow.wallet.entity.AccountEntity;
import com.finflow.wallet.entity.UserEntity;
import com.finflow.wallet.repository.AccountRepository;
import com.finflow.wallet.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.math.BigDecimal;

@Service
public class WalletService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    public WalletService(
            UserRepository userRepository,
            AccountRepository accountRepository
    )
        {
            this.userRepository = userRepository;
            this.accountRepository = accountRepository;
    }

    @Transactional
    public UserEntity createUserWithAccount
            (
            String userName,
            String currency,
            BigDecimal initialBalance
            ) {

        UserEntity user = new UserEntity(userName);

        String accountNumber = generateAccountNumber();

        AccountEntity account = new AccountEntity(
                accountNumber,
                initialBalance,
                currency,
                user
        );

        user.addAccount(account);
        userRepository.save(user);
        // saved automatically because of cascade

        return user;
    }

        private String generateAccountNumber() {
            return "AZ-" + UUID.randomUUID();
        }
    }