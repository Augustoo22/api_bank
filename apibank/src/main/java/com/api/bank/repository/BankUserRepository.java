package com.api.bank.repository;

import com.api.bank.entity.BankUserModel;
import com.api.bank.entity.dto.BankUserForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BankUserRepository extends JpaRepository<BankUserModel, Long> {
    Optional<BankUserModel> findByEmailAndPassword(String userEmail, String userPassword);
}
