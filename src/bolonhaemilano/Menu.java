package bolonhaemilano;

import java.util.Scanner;

public class Menu {

    private Scanner scanner = new Scanner(System.in);
    private boolean emExecucao = true;

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
                System.out.println("Novo jogo");
                break;
            case 2:
                System.out.println("Status da cidade");
                break;
            case 7:
                System.out.println("Jogo de estrategia medieval.");
                break;
            case 0:
                emExecucao = false;
                System.out.println("Jogo encerrado!");
                break;
            default:
                System.out.println("Opcao invalida!");
        }
    }
}