package com.maquininhapagamento;

// Léo: Enum neste caso é como um livro de regras que diz que só aceitamos estes três tipos de pagamentos.
// Léo: Segundo a documentação Java, o enum é uma lista de constantes que podem ser usadas para representar um conjunto fixo de valores.
// Léo: Isso é útil para garantir que o código seja mais legível e menos propenso a erros, pois você não pode acidentalmente usar um valor inválido.
// Léo: O enum é uma classe especial que representa um conjunto fixo de constantes.
// Léo: O enum é uma maneira de definir um tipo de dado que pode ter um número fixo de valores possíveis.

public enum TipoPagamento {
    CREDITO,
    DEBITO,
    QRCODE
}



