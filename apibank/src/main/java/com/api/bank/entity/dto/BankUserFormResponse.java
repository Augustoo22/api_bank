package com.api.bank.entity.dto;

import com.api.bank.entity.BankUserModel;
import com.api.bank.entity.enums.EnumUserType;
import java.util.List;

public record BankUserFormResponse(String username,
                                   String userEmail,
                                   EnumUserType userType) {

    // Response the datas from user model to request by DTO
    public static List<BankUserFormResponse> toUserResponse(List<BankUserModel> users) {
        return users.stream().map(usersResponse -> new BankUserFormResponse(usersResponse.getUsername(), usersResponse.getUserEmail(), usersResponse.getUserType())).toList();
    }

    public static BankUserFormResponse toUserResponse(BankUserModel user) {
        return new BankUserFormResponse(user.getUsername(), user.getUserEmail(), user.getUserType());
    }
}
