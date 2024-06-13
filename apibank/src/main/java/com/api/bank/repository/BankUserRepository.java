package com.api.bank.repository;

import com.api.bank.entity.BankUserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BankUserRepository extends JpaRepository<BankUserModel, UUID> {
    Optional<BankUserModel> findByEmailAndPassword(String userEmailPixKey, String userPassword);
}
