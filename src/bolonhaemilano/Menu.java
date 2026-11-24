package bolonhaemilano;

import java.util.Scanner;

public class Menu {

    private Scanner scanner = new Scanner(System.in);
    private boolean emExecucao = true;
    private Jogador jogador;
    private Cidade cidade;

    public void executar() {
        while (emExecucao) {
            exibirOpcoes();
            processarOpcao(lerOpcao());
        }
    }

    private void exibirOpcoes() {
        System.out.println("\n=== BOLONHA E MILANO ===");
        System.out.println("1 - Novo jogo");
        System.out.println("2 - Ver status da cidade");
        System.out.println("7 - Sobre o jogo");
        System.out.println("0 - Sair");
        System.out.print("Escolha: ");
    }

    private int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void processarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                iniciarNovoJogo();
                break;
            case 2:
                exibirStatus();
                break;
            case 7:
                exibirSobre();
                break;
            case 0:
                emExecucao = false;
                System.out.println("Jogo encerrado!");
                break;
            default:
                System.out.println("Opcao invalida!");
        }
    }

    private void iniciarNovoJogo() {
        System.out.print("Nome do jogador: ");
        String nome = scanner.nextLine();

        System.out.print("Nome da cidade: ");
        String nomeCidade = scanner.nextLine();

        if (nome.trim().isEmpty()) {
            nome = "Jogador 1";
        }

        if (nomeCidade.trim().isEmpty()) {
            nomeCidade = "Bolonha";
        }

        jogador = new Jogador(nome);
        cidade = new Cidade(nomeCidade, jogador);

        System.out.println("Novo jogo iniciado!");
        exibirStatus();
    }

    private void exibirStatus() {
        if (cidade == null) {
            System.out.println("Nenhum jogo em andamento.");
            return;
        }

        cidade.exibirStatus();
    }

    private void exibirSobre() {
        System.out.println("Bolonha e Milano");
        System.out.println("Jogo de estrategia medieval.");
        System.out.println("Governe sua cidade e desenvolva seu reino.");
    }
}