package com.api.bank.entity.dto;

import com.api.bank.entity.BankUserModel;
import com.api.bank.entity.EnumUserType;
import com.api.bank.entity.BankUserType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record BankUserDTO(@NotBlank String username,
                          @NotBlank int userAge,
                          @NotBlank String userEmail,
                          @NotBlank String userPassword,
                          @NotBlank BigDecimal userBalance,
                          @NotNull EnumUserType userType) {
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
