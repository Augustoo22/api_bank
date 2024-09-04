package com.api.bank.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * Classe que representa a entidade Transferencia no sistema.
 *
 * A entidade é mapeada para uma tabela no banco de dados e armazena informações sobre transferências
 */

//@Entity está dizendo que é uma classe JPA que será mapeada para uma tabela do banco de dados
@Entity
public class Transferencia {
    //@Id está indicando que será uma chave primaria
    //@GeneratedValue indica que esse id será gerado automaticamente
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String origem;
    private String destino;
    private double valor;
    private LocalDateTime dataHora;

    // Getters e Setters
    // Métodos GET para acessar e metodos SET eles são importantes para que o JPA consiga fazer a manipulação
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

}