package com.finflow.wallet.service;

import com.finflow.wallet.dto.AccountResponse;
import com.finflow.wallet.entity.AccountEntity;
import com.finflow.wallet.entity.TransactionAuditEntity;
import com.finflow.wallet.entity.UserEntity;
import com.finflow.wallet.repository.AccountRepository;
import com.finflow.wallet.repository.TransactionAuditRepository;
import com.finflow.wallet.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;
import java.math.BigDecimal;

@Service
public class WalletService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final TransactionAuditService auditService;
    private final CurrencyService currencyService;


    public WalletService(
            UserRepository userRepository,
            AccountRepository accountRepository,
            TransactionAuditService auditService,
            CurrencyService currencyService
    ) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.auditService = auditService;
        this.currencyService = currencyService;
    }

    private static final Logger log = LoggerFactory.getLogger(WalletService.class);

    private String generateAccountNumber() {
        return "AZ-" + UUID.randomUUID();
    }

    @Transactional
    public void transfer(
            String fromAccountNumber,
            String toAccountNumber,
            BigDecimal amount
    ) {
        try {
            log.info("Initiating transfer: from={} to={} amount={}",
                    fromAccountNumber, toAccountNumber, amount);

            AccountEntity from = accountRepository.findByAccountNumber(fromAccountNumber)
                    .orElseThrow(() -> new IllegalArgumentException("Source account not found"));

            AccountEntity to = accountRepository.findByAccountNumber(toAccountNumber)
                    .orElseThrow(() -> new IllegalArgumentException("Target account not found"));

            if (from.getBalance().compareTo(amount) < 0) {
                throw new IllegalStateException("Insufficient balance");
            }

            BigDecimal debitAmount = amount;

            BigDecimal creditAmount = currencyService.convert(
                    from.getCurrency(),
                    to.getCurrency(),
                    amount
            );

            from.decreaseBalance(debitAmount);
            to.increaseBalance(creditAmount);

            auditService.record(
                    fromAccountNumber,
                    toAccountNumber,
                    amount,
                    "SUCCESS"
            );

            log.info("Transfer completed successfully");

        } catch (RuntimeException ex) {

            auditService.record(
                    fromAccountNumber,
                    toAccountNumber,
                    amount,
                    "FAILED"
            );

            log.error("Transfer failed", ex);
            throw ex; // REQUIRED for rollback
        }
    }
}

