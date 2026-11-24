# Bolonha e Milano

## Sobre o projeto

Bolonha e Milano é um jogo de estratégia medieval desenvolvido em Java para a disciplina de Técnicas de Desenvolvimento de Sistemas do IFSP.

O jogo permite que até quatro jogadores administrem cidades, gerenciem recursos, desenvolvam sua economia e conquistem títulos de nobreza.

## Funcionalidades

- Gerenciamento de cidades e recursos.
- Compra e venda de grãos e terras.
- Agricultura e distribuição de alimentos.
- Arrecadação de impostos.
- Definição do nível de justiça.
- Recrutamento de soldados e construções.
- Sistema de rodadas anuais.
- Partidas de 1 a 4 jogadores.
- Sistema de prestígio e títulos de nobreza.
- Classificação e condições de vitória.
- Testes automatizados.

## Tecnologias utilizadas

- Java 21
- Eclipse IDE
- Git
- GitHub
- Programação Orientada a Objetos
- UML

## Como compilar

```bash
mkdir -p bin
javac -d bin src/bolonhaemilano/*.java
```

## Como executar

```bash
java -cp bin bolonhaemilano.Main
```

Também é possível executar a classe Main.java diretamente pelo Eclipse.

## Menu principal

| Opção | Funcionalidade |
|---|---|
| 1 | Novo jogo |
| 2 | Ver status da cidade |
| 3 | Mercado |
| 4 | Agricultura |
| 5 | Governo |
| 6 | Construções |
| 7 | Encerrar turno |
| 8 | Ver classificação |
| 9 | Sobre o jogo |
| 0 | Sair |

## Objetivo do jogo

O jogador deve administrar sua cidade e acumular prestígio para conquistar novos títulos de nobreza, começando como Cavaleiro e podendo chegar a Rei ou Rainha.

A partida termina quando um jogador alcança o título máximo ou quando termina o ano de 1410.

## Testes automatizados

Para executar os testes:

```bash
java -cp bin bolonhaemilano.Testes
```

O projeto possui 37 verificações automatizadas. O resultado esperado é:

```text
Aprovados: 37 | Reprovados: 0
```

## Etapas do desenvolvimento

1. Menu principal.
2. Jogador e Cidade.
3. Economia e Mercado.
4. Agricultura e População.
5. Impostos e Governo.
6. Construções e Soldados.
7. Rodadas, Múltiplos Jogadores e Vitória.
8. Integração, Testes e Entrega Final.

## Autoria

Sofia Bali

Instituto Federal de São Paulo — IFSP

Técnico em Desenvolvimento de Sistemas

Professor: Domingos Bernardo

2026
