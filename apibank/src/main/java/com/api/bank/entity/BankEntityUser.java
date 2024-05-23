package com.api.bank.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Usuario")
public class BankEntityUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "IDADE")
    private int idade;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "SENHA")
    private String senha;

    @Column(name = "SALDO")
    private float saldo;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public void setUsuario(String nome,
                           int idade,
                           String email,
                           String senha,
                           float saldo) {

        this.nome = nome;
        this.idade = idade;
        this.email = email;
        this.senha = senha;
        this.saldo = saldo;

    }
}