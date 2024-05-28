package com.api.bank.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_user_type")
public class BankUserType {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String descriptionUserType;

    public BankUserType(Long id, String descriptionUserType) {
        this.id = id;
        this.descriptionUserType = descriptionUserType;
    }
    public BankUserType() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescriptionUserType() {
        return descriptionUserType;
    }

    public void setDescriptionUserType(String descriptionUserType) {
        this.descriptionUserType = descriptionUserType;
    }
}
