package com.api.bank.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "tb_transfer")
public class TransferModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name="user_receiver_id")
    private BankUserModel userReceiver;
    @ManyToOne
    @JoinColumn(name="user_sender_id")
    private BankUserModel userSender;
    @Column(name = "value_transfer")
    private BigDecimal valueTransfer;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public BankUserModel getUserReceiver() {
        return userReceiver;
    }

    public void setUserReceiver(BankUserModel userReceiver) {
        this.userReceiver = userReceiver;
    }

    public BankUserModel getUserSender() {
        return userSender;
    }

    public void setUserSender(BankUserModel userSender) {
        this.userSender = userSender;
    }

    public BigDecimal getValueTransfer() {
        return valueTransfer;
    }

    public void setValueTransfer(BigDecimal valueTransfer) {
        this.valueTransfer = valueTransfer;
    }

    public TransferModel() {}

    public TransferModel(UUID id,
                         BankUserModel userReceiver,
                         BankUserModel userSender,
                         BigDecimal valueTransfer) {
        this.id = id;
        this.userReceiver = userReceiver;
        this.userSender = userSender;
        this.valueTransfer = valueTransfer;
    }
}
