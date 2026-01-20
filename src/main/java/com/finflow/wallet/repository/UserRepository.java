package com.finflow.wallet.repository;

import com.finflow.wallet.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UserEntity, Long> {


}
