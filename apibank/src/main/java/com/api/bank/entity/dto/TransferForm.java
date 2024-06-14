package com.api.bank.entity.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record TransferForm(
        @DecimalMin("0.01") @NotNull BigDecimal valueTransfer,
        @NotNull UUID payer,
        @NotNull UUID payee
        ) {
}
