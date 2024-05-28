package com.api.bank.entity;

public enum EnumUserType {
    USER(1L, "user"),
    ADMIN(2L, "admin");

    private final Long id;
    private final String description;

    EnumUserType(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public BankUserType get() {
        return new BankUserType(id, description);
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
