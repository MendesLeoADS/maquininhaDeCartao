package com.maquininhapagamento;

import java.io.IOException;

public class App {
    public static void main(String[] args) {
        // Léo: Criando a Maquininha.
        Maquininha maquininha = new Maquininha();

        // Léo: Processando alguns pagamentos.
        try {
        Transacao transacao1 = maquininha.processarPagamento(50.0, TipoPagamento.CREDITO);
        Transacao transacao2 = maquininha.processarPagamento(45.99, TipoPagamento.DEBITO);
        Transacao transacao3 = maquininha.processarPagamento(13.99, TipoPagamento.QRCODE);
        
        // Léo: Exibindo transações processadas.
        System.out.println("Transações processadas:");
        System.out.println("1: " + transacao1);
        System.out.println("2: " + transacao2);
        System.out.println("3: " + transacao3);
    } catch (IllegalArgumentException e) {
            System.out.println("Erro ao processar pagamento: " + e.getMessage());
        }

        // Léo: Cancelando uma transação.
        try {
            System.out.println("\nCancelando a transação de índice 1 (Débito de 30.0):");
            maquininha.cancelarTransacao(1);
            System.out.println("Histórico após cancelamento:");
            for (Transacao transacao : maquininha.getHistoricoTransacoes()) {
                System.out.println(transacao);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao cancelar transação: " + e.getMessage());
        }


        // Léo: Gerando um QR Code.
        try {
            String qrCode = maquininha.gerarQRCode(100.0);
            System.out.println("\nQR Code gerado: " + qrCode);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao gerar QR Code: " + e.getMessage());
        }


        // Léo: Consultando transações por tipo (apenas CREDITO).
        System.out.println("\nConsultando transações do tipo CREDITO:");
        for (Transacao transacao : maquininha.consultarTransacoesPorTipo(TipoPagamento.CREDITO)) {
            System.out.println(transacao);
        }


        // Léo: Tentando processar um pagamento inválido.
        try {
            maquininha.processarPagamento(-10.0,TipoPagamento.DEBITO);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao processar pagamento inválido: " + e.getMessage());
        }


        // Léo: Exibindo o histórico de transações.
        System.out.println("Histórico das Transações processadas:");
        for (Transacao transacao : maquininha.getHistoricoTransacoes()) {
            System.out.println(transacao);
        }


        // Léo: Exportando o histórico de transações para um arquivo.
        try {
            maquininha.exportarHistoricoParaArquivo("historico_transacoes.txt");
            System.out.println("\nHistórico exportado para 'historico_transacoes.txt'");
        } catch (IOException e) {
            System.out.println("Erro ao exportar histórico: " + e.getMessage());
        }
    }
}