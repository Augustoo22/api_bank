package com.api.bank.service;

import com.api.bank.entity.BankUserModel;
import com.api.bank.entity.TransferModel;
import com.api.bank.entity.dto.TransferForm;
import com.api.bank.repository.BankUserRepository;
import com.api.bank.repository.TransferRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransferService {

    @Autowired
    private TransferRepository transferRepository;

    @Autowired
    private BankUserRepository bankUserRepository;

    @Transactional
    public String transfer(TransferForm transferForm) {
        Optional<BankUserModel> sender = bankUserRepository.findById(transferForm.payer());
        if (sender.isEmpty()) {
            return "Error: Payer user not found with ID: " + transferForm.payer();
        }

        Optional<BankUserModel> receiver = bankUserRepository.findById(transferForm.payee());
        if (receiver.isEmpty()) {
            return "Error: Payee user not found with ID: " + transferForm.payee();
        }

        BankUserModel senderModel = sender.get();
        BankUserModel receiverModel = receiver.get();

        if (!senderModel.isBalanceEqualOrGreaterThan(transferForm.valueTransfer())) {
            return "Error: Insufficient balance for user ID: " + transferForm.payer();
        }

        senderModel.debit(transferForm.valueTransfer());
        receiverModel.credit(transferForm.valueTransfer());

        TransferModel transfer = new TransferModel(receiverModel, senderModel, transferForm.valueTransfer());

        bankUserRepository.save(senderModel);
        bankUserRepository.save(receiverModel);
        TransferModel transferResult = transferRepository.save(transfer);

        return "Transfer successful: Value: " + transferResult.getValueTransfer() +
                ", Payer ID: " + transferResult.getUserSender().getId() +
                ", Payee ID: " + transferResult.getUserReceiver().getId();
    }

    @Transactional
    public List<TransferModel> getTransfersByUserId(UUID userId) {
        return transferRepository.findByUserReceiverIdOrUserSenderId(userId, userId);
    }
}
