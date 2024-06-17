package com.api.bank.model.dto;

import com.api.bank.model.Transferencia;

import java.time.format.DateTimeFormatter;

public class TransferenciaDTO {
    private Long id;
    private String origem;
    private String destino;
    private double valor;
    private String dataHora;

    public TransferenciaDTO(Transferencia transferencia) {
        this.id = transferencia.getId();
        this.origem = transferencia.getOrigem();
        this.destino = transferencia.getDestino();
        this.valor = transferencia.getValor();
        if (transferencia.getDataHora() != null) {
            this.dataHora = transferencia.getDataHora().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        }
    }

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

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }
}