package com.api.bank.repository;

import com.api.bank.entity.BankUserType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankUserTypeRepository extends JpaRepository<BankUserType, Long> {
}
