package com.api.bank.entity.dto;

public record BankUserFormLogin(
        String userEmail,
        String userPassword
) {
}
