package com.maquininhapagamento;

import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Maquininha maquininha = new Maquininha();
        try (Scanner scanner = new Scanner(System.in)) {
            boolean rodando = true;

            while (rodando) {
                System.out.println("\n=== Maquininha de Pagamento ===");
                System.out.println("1. Processar Pagamento");
                System.out.println("2. Cancelar Transação");
                System.out.println("3. Gerar QR Code");
                System.out.println("4. Consultar Transações por Tipo");
                System.out.println("5. Exibir Histórico");
                System.out.println("6. Exportar Histórico para Arquivo");
                System.out.println("7. Sair");
                System.out.print("Escolha uma opção: ");

                int opcao = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer

                switch (opcao) {
                    case 1 -> { // Processar Pagamento
                        System.out.print("Digite o valor do pagamento: ");
                        double valor = scanner.nextDouble();
                        scanner.nextLine(); // Limpar o buffer

                        System.out.println("Tipos de pagamento disponíveis: ");
                        System.out.println("1. CREDITO");
                        System.out.println("2. DEBITO");
                        System.out.println("3. QRCODE");
                        System.out.print("Escolha o tipo de pagamento (1-3): ");
                        int tipoOpcao = scanner.nextInt();
                        scanner.nextLine(); // Limpar o buffer

                        TipoPagamento tipoPagamento;
                        switch (tipoOpcao) {
                            case 1 -> tipoPagamento = TipoPagamento.CREDITO;
                            case 2 -> tipoPagamento = TipoPagamento.DEBITO;
                            case 3 -> tipoPagamento = TipoPagamento.QRCODE;
                            default -> {
                                System.out.println("Tipo de pagamento inválido!");
                                continue;
                            }
                        }

                        System.out.print("Digite a senha: ");
                        String senha = scanner.nextLine();

                        try {
                            Transacao transacao = maquininha.processarPagamento(valor, tipoPagamento, senha);
                            System.out.println("Pagamento processado: " + transacao);
                        } catch (IllegalArgumentException | SecurityException e) {
                            System.out.println("Erro: " + e.getMessage());
                        }
                    }

                    case 2 -> { // Cancelar Transação
                        System.out.println("Histórico atual:");
                        for (int i = 0; i < maquininha.getHistoricoTransacoes().size(); i++) {
                            System.out.println(i + ": " + maquininha.getHistoricoTransacoes().get(i));
                        }
                        System.out.print("Digite o índice da transação para cancelar: ");
                        int indice = scanner.nextInt();
                        scanner.nextLine(); // Limpar o buffer

                        System.out.print("Digite a senha: ");
                        String senha = scanner.nextLine();

                        try {
                            maquininha.cancelarTransacao(indice, senha);
                            System.out.println("Transação cancelada com sucesso!");
                        } catch (IllegalArgumentException | SecurityException e) {
                            System.out.println("Erro: " + e.getMessage());
                        }
                    }

                    case 3 -> { // Gerar QR Code
                        System.out.print("Digite o valor para o QR Code: ");
                        double valor = scanner.nextDouble();
                        scanner.nextLine(); // Limpar o buffer

                        try {
                            String qrCode = maquininha.gerarQRCode(valor);
                            System.out.println("QR Code gerado: " + qrCode);
                        } catch (IllegalArgumentException e) {
                            System.out.println("Erro: " + e.getMessage());
                        }
                    }

                    case 4 -> { // Consultar Transações por Tipo
                        System.out.println("Tipos de pagamento disponíveis: ");
                        System.out.println("1. CREDITO");
                        System.out.println("2. DEBITO");
                        System.out.println("3. QRCODE");
                        System.out.print("Escolha o tipo de pagamento (1-3): ");
                        int tipoOpcao = scanner.nextInt();
                        scanner.nextLine(); // Limpar o buffer

                        TipoPagamento tipoPagamento;
                        switch (tipoOpcao) {
                            case 1 -> tipoPagamento = TipoPagamento.CREDITO;
                            case 2 -> tipoPagamento = TipoPagamento.DEBITO;
                            case 3 -> tipoPagamento = TipoPagamento.QRCODE;
                            default -> {
                                System.out.println("Tipo de pagamento inválido!");
                                continue;
                            }
                        }

                        System.out.println("Transações do tipo " + tipoPagamento + ":");
                        for (Transacao transacao : maquininha.consultarTransacoesPorTipo(tipoPagamento)) {
                            System.out.println(transacao);
                        }
                    }

                    case 5 -> { // Exibir Histórico
                        System.out.println("Histórico de transações:");
                        for (Transacao transacao : maquininha.getHistoricoTransacoes()) {
                            System.out.println(transacao);
                        }
                    }

                    case 6 -> { // Exportar Histórico
                        System.out.print("Digite o nome do arquivo (ex.: historico.txt): ");
                        String nomeArquivo = scanner.nextLine();
                        try {
                            maquininha.exportarHistoricoParaArquivo(nomeArquivo);
                            System.out.println("Histórico exportado para " + nomeArquivo);
                        } catch (IOException e) {
                            System.out.println("Erro ao exportar histórico: " + e.getMessage());
                        }
                    }

                    case 7 -> { // Sair
                        rodando = false;
                        System.out.println("Saindo da maquininha. Até logo!");
                    }

                    default -> System.out.println("Opção inválida! Tente novamente.");
                }
            }
        }
    }
}