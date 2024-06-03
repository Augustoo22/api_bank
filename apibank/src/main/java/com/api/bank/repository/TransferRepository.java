package com.api.bank.repository;

import com.api.bank.entity.TransferModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransferRepository extends JpaRepository<TransferModel, UUID> {
}
