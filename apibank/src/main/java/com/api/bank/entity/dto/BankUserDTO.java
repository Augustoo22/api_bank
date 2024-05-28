package com.api.bank.entity.dto;

import com.api.bank.entity.BankUserModel;
import com.api.bank.entity.EnumUserType;
import com.api.bank.entity.BankUserType;

public record BankUserDTO(String username,
                          int userAge,
                          String userEmail,
                          String userPassword,
                          float userBalance,
                          EnumUserType userType) {
    public BankUserModel toUser(){
        BankUserType bankUserTypeEntity = userType.get();
        return new BankUserModel(
                username,
                userAge,
                userEmail,
                userPassword,
                userBalance,
                bankUserTypeEntity
        );
    }
}
