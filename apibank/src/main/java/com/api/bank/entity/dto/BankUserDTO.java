package com.api.bank.entity.dto;

import com.api.bank.entity.BankUserModel;
import com.api.bank.entity.enums.EnumUserType;
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
        return new BankUserModel(
                username,
                userAge,
                userEmail,
                userPassword,
                userBalance,
                userType
        );
    }
    public static BankUserDTO fromUser(BankUserModel bankUser) {
        return new BankUserDTO(
                bankUser.getUsername(),
                bankUser.getUserAge(),
                bankUser.getUserEmail(),
                bankUser.getUserPassword(),
                bankUser.getUserBalance(),
                bankUser.getBankUserType()
        );
    }
}
