package com.maquininhapagamento;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;



public class Maquininha {
    private final List<Transacao> historicoTransacoes;


    public Maquininha(){
        this.historicoTransacoes = new ArrayList<>();
    }


    //Léo: Adicionei um método para exportar o histórico de transações para um arquivo.
    public void exportarHistoricoParaArquivo(String caminhoArquivo) throws IOException {
        try (FileWriter writer = new FileWriter(caminhoArquivo)){
            writer.write("Histórico de Transações\n");
            writer.write("====================================\n");
            for (Transacao transacao : historicoTransacoes) {
                writer.write(transacao.toString() + "\n");
            }
        }
    }

    // Léo: Adicionei o método para processar um Pagamento.
    public Transacao processarPagamento(double valor, TipoPagamento tipoPagamento) {
        double valorFinal = valor;
        if (tipoPagamento == TipoPagamento.CREDITO) {
            double taxa = valor * 0.05; // Taxa de 5% para crédito.
            valorFinal = valor + taxa;
        }
        Transacao transacao = new Transacao(valorFinal, tipoPagamento);
        historicoTransacoes.add(transacao);
        return transacao;
    }

    // Léo: Adicionei o método para gerar um QR Code.
    public String gerarQRCode(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor deve ser maior que zero.");
        }
    String idUnico = UUID.randomUUID().toString();
    String qrCodeData = "PAGAMENTO: valor=" + valor + ", id=" + idUnico;
    return qrCodeData;
    }


    // Léo: Adicionei o método para consultar o histórico de transações.
    public List<Transacao> getHistoricoTransacoes(){
        return new ArrayList<>(historicoTransacoes);
    }


    // Léo: Adicionei o método para consultar transações por tipo de pagamento.
    public List<Transacao> consultarTransacoesPorTipo(TipoPagamento tipoPagamento) {
        List<Transacao> transacoesFiltradas = new ArrayList<>();
        for (Transacao transacao : historicoTransacoes) {
            if (transacao.getTipoPagamento() == tipoPagamento) {
                transacoesFiltradas.add(transacao);
            }
        }
        return transacoesFiltradas;
    }


    // Léo: Adicionei o método para cancelar transações.
    public void cancelarTransacao(int indice) {
        if (indice < 0 || indice >= historicoTransacoes.size() ) {
            throw new IllegalArgumentException("Índice inválido: " + indice);
        }
        historicoTransacoes.remove(indice);
    }
}

