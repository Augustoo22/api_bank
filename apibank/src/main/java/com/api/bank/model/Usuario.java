package com.api.bank.model;

import jakarta.persistence.*;

/**
 * Classe que representa a entidade Usuários no sistema.
 *
 * A entidade é mapeada para uma tabela no banco de dados e armazena informações sobre usuários.
 */

//@Entity está dizendo que é uma classe JPA que será mapeada para uma tabela do banco de dados
//@Table Aqui passa o nome da tabela do banco de dados que será criada ao executar o codigo
@Entity
@Table(name = "usuarios")
public class Usuario {
    //@Id está indicando que será uma chave primaria
    //@GeneratedValue indica que esse id será gerado automaticamente
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private int idade;
    private String email;
    private String senha;
    private double saldo;
    private String cpf;

    // Getters e Setters
    // Métodos GET para acessar e metodos SET eles são importantes para que o JPA consiga fazer a manipulação

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }


    // Métodos débito e credito, nos quais tiram ou depositam o valor de saldo na conta do usuário especificado.
    public void debit(double valor) {
        this.saldo -= valor;
    }

    public void credit(double valor) {
        this.saldo += valor;
    }
}
