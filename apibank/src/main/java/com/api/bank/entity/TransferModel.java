package com.api.bank.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "tb_transfer")
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    public TransferModel(BankUserModel userReceiver, BankUserModel userSender, BigDecimal valueTransfer) {
        this.userReceiver = userReceiver;
        this.userSender = userSender;
        this.valueTransfer = valueTransfer;
    }
}
