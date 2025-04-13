# Maquininha de Pagamento

Este projeto é uma aplicação Java que simula uma maquininha de pagamento para uma Empresa. A aplicação permite processar pagamentos, gerar QR Codes, consultar e cancelar transações, exportar o histórico e inclui autenticação para operações sensíveis. O desenvolvimento foi feito de forma iterativa, com boas práticas de programação e validações para garantir um código robusto.

## Estrutura do Projeto
O projeto está organizado no pacote `com.maquininhapagamento` e contém as seguintes classes:
- **App.java**: Classe principal que executa o programa e contém a interface de usuário no terminal.
- **Maquininha.java**: Classe que representa a maquininha de pagamento, com métodos para processar pagamentos, cancelar transações, gerar QR Codes, consultar e exportar o histórico.
- **Transacao.java**: Classe que modela uma transação, com atributos como valor, tipo de pagamento e data/hora.
- **TipoPagamento.java**: Enumeração que define os tipos de pagamento aceitos (CREDITO, DEBITO, QRCODE).

### Pré-requisitos
- **Java 23** (ou superior) instalado.
- **Maven** para gerenciar dependências e compilar o projeto.
- Um terminal para executar os comandos Maven.

## Como Executar o Projeto
1. Clone o repositório ou copie os arquivos para uma pasta local.
2. Navegue até a pasta do projeto:
   ```bash
   cd maquininha-pagamento
   ```
3. Compile o projeto com o Maven:
   ```bash
   mvn clean install
   ```
4. Execute o programa:
   ```bash
   mvn exec:java -Dexec.mainClass="com.maquininhapagamento.App"
   ```
5. Siga as instruções no terminal para interagir com a maquininha.

## Funcionalidades Implementadas

### 1. Estrutura Inicial
- Criamos a estrutura básica do projeto com o Maven.
- Definimos as classes `Transacao` e `TipoPagamento` para modelar transações e tipos de pagamento.
- Criamos a classe `Maquininha` com um atributo `historicoTransacoes` (uma lista para armazenar transações).
- Implementamos o método `processarPagamento` para criar e adicionar transações ao histórico.

### 2. Correção de Aviso "Field can be final"
- O VS Code apontou que o atributo `historicoTransacoes` poderia ser `final`, já que ele não era reatribuído após a inicialização.
- Adicionamos o modificador `final` ao atributo `historicoTransacoes` na classe `Maquininha`, garantindo maior segurança e seguindo boas práticas.

### 3. Validações e Novas Funcionalidades
- **Validações:** Adicionamos validações na classe `Transacao` para impedir valores menores ou iguais a zero, lançando uma `IllegalArgumentException` se a validação falhar.
- **QR Code:** Implementamos o método `gerarQRCode` na classe `Maquininha`, que gera um QR Code simulado como uma string com um valor e um identificador único (usando `UUID`).
- **Consulta por Tipo:** Adicionamos o método `consultarTransacoesPorTipo` para filtrar transações por tipo de pagamento.

### 4. Cancelamento, Taxas e Exportação
- **Cancelamento de Transações:** Adicionamos o método `cancelarTransacao` para remover transações do histórico com base no índice.
- **Taxas:** Modificamos o método `processarPagamento` para adicionar uma taxa de 5% para pagamentos no crédito.
- **Exportação do Histórico:** Implementamos o método `exportarHistoricoParaArquivo` para salvar o histórico de transações em um arquivo de texto.

### 5. Interface de Usuário e Autenticação
- **Interface de Usuário:** Atualizamos o `App.java` para incluir um menu interativo no terminal, permitindo ao usuário escolher opções como processar pagamento, cancelar transação, gerar QR Code, consultar transações, exibir histórico e exportar o histórico.
- **Autenticação:** Adicionamos um sistema de autenticação na classe `Maquininha`, com uma senha fixa ("1234"). Os métodos `processarPagamento` e `cancelarTransacao` agora exigem a senha, lançando uma `SecurityException` se a senha estiver incorreta.

### 6. Correções de Erros e Melhorias
- Corrigimos erros no `App.java` e `Maquininha.java`, ajustando as assinaturas dos métodos `processarPagamento` e `cancelarTransacao` para incluir a senha.
- Resolvemos avisos do VS Code:
  - Ajustamos o uso da variável `valorFinal` no método `processarPagamento`.
  - Convertemos os `switch` tradicionais no `App.java` para a sintaxe de "rule switch" (usando `->`), seguindo a sugestão do Java para uma sintaxe mais moderna e concisa.

## Exemplo de Uso
Ao executar o programa, o usuário verá um menu no terminal:
```
=== Maquininha de Pagamento ===
1. Processar Pagamento
2. Cancelar Transação
3. Gerar QR Code
4. Consultar Transações por Tipo
5. Exibir Histórico
6. Exportar Histórico para Arquivo
7. Sair
Escolha uma opção: 
```
- Escolha uma opção digitando um número (1 a 7).
- Para processar ou cancelar transações, a senha "1234" será solicitada.
- O histórico pode ser exportado para um arquivo (ex.: `historico.txt`).

## Possíveis Melhorias Futuras
- Adicionar uma interface gráfica (ex.: com JavaFX).
- Implementar suporte a múltiplos usuários com senhas diferentes.
- Criar testes unitários para garantir a robustez do código.
- Adicionar mais validações ou relatórios (ex.: total de vendas por tipo de pagamento).

## Conclusão
O projeto da maquininha de pagamento foi desenvolvido com foco em boas práticas de programação, como validações, segurança (autenticação) e uma interface amigável. Ele simula as funcionalidades básicas de uma maquininha de pagamento de forma eficiente e pode ser expandido com novas funcionalidades no futuro.