package com.finflow.wallet.repository;

import com.finflow.wallet.entity.AcccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AcccountEntity, Long> { }
