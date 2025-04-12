package com.maquininhapagamento;

import java.time.LocalDateTime;

public class Transacao {
    private double valor;
    private TipoPagamento tipoPagamento;
    private LocalDateTime dataHora;

    // Léo: Construtor.
    public Transacao(double valor, TipoPagamento tipoPagamento) {
        if (valor <=0) {
            throw new IllegalArgumentException("O valor da Transação deve ser maior que zero.");
        }
        this.valor = valor;
        this.tipoPagamento = tipoPagamento;
        this.dataHora = LocalDateTime.now();
    }

    // Léo: Getters e Setters.
    public double getValor(){
        return valor;
    }

    public void setValor(double valor){
        if (valor <=0) {
            throw new IllegalArgumentException("O valor da Transação deve ser maior que zero.");
        }
        this.valor = valor;
    }

    public TipoPagamento getTipoPagamento(){
        return tipoPagamento;
    }

    public void setTipoPagamento(TipoPagamento tipoPagamento){
        this.tipoPagamento = tipoPagamento;
    }

    public LocalDateTime getDataHora(){
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora){
        this.dataHora = dataHora;
    }

    // Léo: Exibição da transação.
    @Override
    public String toString(){
        return "Transação [valor=" + valor + ", tipoPagamento=" + tipoPagamento + ", dataHora=" + dataHora + "]";
    }
}