package com.finflow.wallet.config;

import com.finflow.wallet.service.WalletService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class TestDataConfig {

    @Bean
    CommandLineRunner testWallet(WalletService walletService) {
        return args -> {
            walletService.createUserWithAccount(
                    "Riad",
                    "AZN",
                    new BigDecimal("1000.00")
            );
        };
    }
}
