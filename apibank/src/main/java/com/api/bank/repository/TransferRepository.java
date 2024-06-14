package com.api.bank.repository;

import com.api.bank.entity.TransferModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransferRepository extends JpaRepository<TransferModel, UUID> {
    List<TransferModel> findByUserReceiverIdOrUserSenderId(UUID userReceiverId, UUID userSenderId);
}
