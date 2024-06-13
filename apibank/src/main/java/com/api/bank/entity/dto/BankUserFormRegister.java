package com.api.bank.entity.dto;

import com.api.bank.entity.BankUserModel;
import com.api.bank.entity.enums.EnumUserType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record BankUserFormRegister(@NotBlank String username,
                                   @NotBlank int userAge,
                                   @NotBlank String userEmailPixKey,
                                   @NotBlank String userPassword,
                                   @NotBlank BigDecimal userBalance,
                                   @NotNull String userCPF,
                                   @NotNull EnumUserType userType) {

    // Implemented DTO to instantiate an User object
    public static BankUserModel toUserRequest(BankUserFormRegister user) {
        return new BankUserModel( user.username,
                                  user.userAge,
                                  user.userEmailPixKey,
                                  user.userPassword,
                                  user.userBalance,
                                  user.userCPF,
                                  user.userType);
    }
}