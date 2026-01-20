package com.finflow.wallet.repository;

import com.finflow.wallet.entity.TransactionAuditEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionAuditRepository
    extends JpaRepository<TransactionAuditEntity, Long> {

}
