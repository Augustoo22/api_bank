package com.api.bank.entity.enums;

public enum EnumUserType {
    ADMIN("admin"),
    USER("user");

    private String role;

    EnumUserType(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
