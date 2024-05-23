package com.api.bank.repository;

import com.api.bank.entity.BankEntityUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankRepository extends JpaRepository<BankEntityUser, Long> {
    List<BankEntityUser> findByNomeContainingIgnoreCase(String nome);
}
