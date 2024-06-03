package com.api.bank.entity;

import com.api.bank.entity.enums.EnumUserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "tb_usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankUserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "USER_ID")
    private UUID id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "USER_AGE")
    private int userAge;

    @Column(name = "USER_EMAIL")
    private String userEmail;

    @Column(name = "USER_PASSWORD")
    private String userPassword;

    @Column(name = "USER_BALANCE")
    private BigDecimal userBalance;

    @ManyToOne
    @JoinColumn(name="user_type_id")
    private EnumUserType bankUserType;

    public BankUserModel(String username,
                         int userAge,
                         String userEmail,
                         String userPassword,
                         BigDecimal userBalance,
                         EnumUserType bankUserType) {
        this.username = username;
        this.userAge = userAge;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userBalance = userBalance;
        this.bankUserType = bankUserType;
    }

    public void setUsuario(String username,
                           int userAge,
                           String userEmail,
                           String userPassword,
                           BigDecimal userBalance) {

        this.username = username;
        this.userAge = userAge;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userBalance = userBalance;

    }
}