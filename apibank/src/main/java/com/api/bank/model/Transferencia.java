package com.api.bank.model;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
//'@Entity está dizendo que é uma classe JPA que será mapeada para uma tabela do banco de dados

public class Transferencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Id está indicando que será uma chave primaria
    //@GeneratedValue indica que esse id será gerado automaticamente
    private Long id;
    private String origem;
    private String destino;
    private double valor;
    private LocalDateTime dataHora;

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