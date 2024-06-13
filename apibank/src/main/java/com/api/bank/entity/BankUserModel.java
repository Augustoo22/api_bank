package com.api.bank.entity;

import com.api.bank.entity.enums.EnumUserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "tb_bank_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankUserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "USER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "USER_AGE")
    private int userAge;

    @Column(name = "USER_EMAIL", unique = true)
    private String userEmail;

    @Column(name = "USER_PASSWORD")
    private String userPassword;

    @Column(name = "USER_BALANCE")
    private BigDecimal userBalance;

    @Column(name = "USER_CPF", unique = true)
    private String userCpf;

    @Enumerated(EnumType.STRING)
    private EnumUserType userType;

    public BankUserModel(String username,
                         int userAge,
                         String userEmail,
                         String userPassword,
                         BigDecimal userBalance,
                         String userCPF,
                         EnumUserType bankUserType) {
        this.username = username;
        this.userAge = userAge;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userBalance = userBalance;
        this.userCpf = userCPF;
        this.userType = bankUserType;
    }
    public boolean isBalancerEqualOrGreatherThan(BigDecimal value) {
        return this.userBalance.doubleValue() >= value.doubleValue();
    }

    public void debit(BigDecimal value) {
        this.userBalance = this.userBalance.subtract(value);
    }

    public void credit(BigDecimal value) {
        this.userBalance = this.userBalance.add(value);
    }

}