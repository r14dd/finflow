package com.finflow.wallet.service;

import com.finflow.wallet.entity.TransactionAuditEntity;
import com.finflow.wallet.repository.TransactionAuditRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class TransactionAuditService {

    private final TransactionAuditRepository auditRepository;

    public TransactionAuditService(TransactionAuditRepository auditRepository) {
        this.auditRepository = auditRepository;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void record(
            String fromAccount,
            String toAccount,
            BigDecimal amount,
            String status
    ) {
        auditRepository.save(
                new TransactionAuditEntity(
                        fromAccount,
                        toAccount,
                        amount,
                        status
                )
        );
    }
}
