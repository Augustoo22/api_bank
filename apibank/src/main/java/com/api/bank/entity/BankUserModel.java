package com.api.bank.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "tb_usuario")
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
    private float userBalance;

    @ManyToOne
    @JoinColumn(name="user_type_id")
    private BankUserType bankUserType;

    public BankUserModel() {}

    public BankUserModel(String username,
                         int userAge,
                         String userEmail,
                         String userPassword,
                         float userBalance,
                         BankUserType bankUserType) {
        this.username = username;
        this.userAge = userAge;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userBalance = userBalance;
        this.bankUserType = bankUserType;
    }

    public BankUserModel(UUID id,
                         String username,
                         int userAge,
                         String userEmail,
                         String userPassword,
                         float userBalance,
                         BankUserType bankUserType) {
        this.id = id;
        this.username = username;
        this.userAge = userAge;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userBalance = userBalance;
        this.bankUserType = bankUserType;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public float getUserBalance() {
        return userBalance;
    }

    public void setUserBalance(float userBalance) {
        this.userBalance = userBalance;
    }

    public BankUserType getUserType() {
        return bankUserType;
    }

    public void setUserType(BankUserType bankUserType) {
        this.bankUserType = bankUserType;
    }

    public void setUsuario(String username,
                           int userAge,
                           String userEmail,
                           String userPassword,
                           float userBalance) {

        this.username = username;
        this.userAge = userAge;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userBalance = userBalance;

    }
}