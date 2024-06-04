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
import java.util.stream.Collectors;

@Service
public class TransferService {

    @Autowired
    private TransferRepository transferRepository;

    @Autowired
    private BankUserRepository bankUserRepository;

    @Transactional
    public String transfer(TransferForm transferForm) {
        var sender = bankUserRepository.findById(transferForm.payer());
        if (sender.isEmpty()) {
            return "Error: Payer user not found with ID: " + transferForm.payer();
        }

        var receiver = bankUserRepository.findById(transferForm.payee());
        if (receiver.isEmpty()) {
            return "Error: Payee user not found with ID: " + transferForm.payee();
        }

        BankUserModel senderModel = sender.get();
        BankUserModel receiverModel = receiver.get();

        senderModel.debit(transferForm.valueTransfer());
        receiverModel.credit(transferForm.valueTransfer());

        var transfer = new TransferModel(senderModel, receiverModel, transferForm.valueTransfer());

        bankUserRepository.save(senderModel);
        bankUserRepository.save(receiverModel);
        var transferResult = transferRepository.save(transfer);

        return "Transfer successful: Value: " + transferResult.getValueTransfer() +
                ", Payer ID: " + transferResult.getUserSender().getId() +
                ", Payee ID: " + transferResult.getUserReceiver().getId();
    }
    @Transactional
    public List<TransferModel> getTransfersByUserId(Long userId) {
        List<TransferModel> transfers = transferRepository.findAll();
        return transfers.stream()
                .filter(transfer -> transfer.getUserSender().getId().equals(userId) ||
                        transfer.getUserReceiver().getId().equals(userId))
                .collect(Collectors.toList());
    }
}

