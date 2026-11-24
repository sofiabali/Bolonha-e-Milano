package bolonhaemilano;

import java.util.Scanner;

public class Menu {

    private Scanner scanner = new Scanner(System.in);
    private boolean emExecucao = true;
    private Jogador jogador;
    private Cidade cidade;
    private Mercado mercado = new Mercado();
    private Agricultura agricultura = new Agricultura();
    private Governo governo = new Governo();

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
        System.out.println("3 - Mercado");
        System.out.println("4 - Agricultura");
        System.out.println("5 - Governo");
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
            case 3:
                abrirMercado();
                break;
            case 4:
                abrirAgricultura();
                break;
            case 5:
                abrirGoverno();
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

    private int lerQuantidade() {
        System.out.print("Quantidade: ");
        int qtd = lerOpcao();

        if (qtd <= 0) {
            System.out.println("Quantidade invalida!");
            return 0;
        }

        return qtd;
    }

    private void abrirMercado() {
        if (cidade == null) {
            System.out.println("Inicie um jogo primeiro!");
            return;
        }

        boolean noMercado = true;

        while (noMercado) {
            System.out.println("\n=== MERCADO ===");
            System.out.println("Florins: " + cidade.getFlorins());
            System.out.println("Graos: " + cidade.getGraos());
            System.out.println("Terras: " + cidade.getTerras());

            System.out.println("Preco do grao: " + mercado.getPrecoGrao());
            System.out.println("Preco da terra: " + mercado.getPrecoTerra());

            System.out.println("1 - Comprar graos");
            System.out.println("2 - Vender graos");
            System.out.println("3 - Comprar terras");
            System.out.println("4 - Vender terras");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");

            int opcao = lerOpcao();

            if (opcao == 0) {
                noMercado = false;
                continue;
            }

            if (opcao < 1 || opcao > 4) {
                System.out.println("Opcao invalida!");
                continue;
            }

            int qtd = lerQuantidade();

            if (qtd == 0) {
                continue;
            }

            boolean sucesso = false;

            switch (opcao) {
                case 1:
                    sucesso = mercado.comprarGraos(cidade, qtd);
                    break;
                case 2:
                    sucesso = mercado.venderGraos(cidade, qtd);
                    break;
                case 3:
                    sucesso = mercado.comprarTerras(cidade, qtd);
                    break;
                case 4:
                    sucesso = mercado.venderTerras(cidade, qtd);
                    break;
            }

            if (sucesso) {
                System.out.println("Operacao realizada com sucesso!");
            } else {
                System.out.println("Operacao recusada!");
            }
        }
    }

    private void abrirAgricultura() {

        if (cidade == null) {
            System.out.println("Inicie um jogo primeiro!");
            return;
        }

        boolean naAgricultura = true;

        while (naAgricultura) {

            System.out.println("\n=== AGRICULTURA ===");
            System.out.println("Terras: " + cidade.getTerras());
            System.out.println("Servos: " + cidade.getServos());
            System.out.println("Graos: " + cidade.getGraos());

            System.out.println("1 - Colher");
            System.out.println("2 - Distribuir alimentos");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");

            int opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    colher();
                    break;
                case 2:
                    distribuirAlimentos();
                    break;
                case 0:
                    naAgricultura = false;
                    break;
                default:
                    System.out.println("Opcao invalida!");
            }
        }
    }

    private void colher() {
        int producao = agricultura.processarColheita(cidade);

        System.out.println("Colheita realizada!");
        System.out.println("Graos produzidos: " + producao);
        System.out.println("Estoque atual: " + cidade.getGraos());
    }

    private void distribuirAlimentos() {

        System.out.print("Percentual de graos (20 a 80): ");
        int percentual = lerOpcao();

        int servosAntes = cidade.getServos();

        if (agricultura.distribuirAlimentos(cidade, percentual)) {

            System.out.println("Alimentos distribuidos!");
            System.out.println("Servos antes: " + servosAntes);
            System.out.println("Servos agora: " + cidade.getServos());
            System.out.println("Graos restantes: " + cidade.getGraos());

        } else {
            System.out.println("Percentual invalido!");
        }
    }

    private void abrirGoverno() {

        if (cidade == null) {
            System.out.println("Inicie um jogo primeiro!");
            return;
        }

        boolean noGoverno = true;

        while (noGoverno) {

            System.out.println("\n=== GOVERNO ===");
            System.out.println("Florins: " + cidade.getFlorins());
            System.out.println("Impostos: " + cidade.getTaxaImposto() + "%");
            System.out.println("Justica: " + cidade.getNivelJustica());

            System.out.println("1 - Arrecadar impostos");
            System.out.println("2 - Definir taxa de imposto");
            System.out.println("3 - Definir nivel de justica");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");

            switch (lerOpcao()) {
                case 1:
                    arrecadarImpostos();
                    break;
                case 2:
                    definirTaxaImposto();
                    break;
                case 3:
                    definirJustica();
                    break;
                case 0:
                    noGoverno = false;
                    break;
                default:
                    System.out.println("Opcao invalida!");
            }
        }
    }

    private void arrecadarImpostos() {

        int valor = governo.arrecadarImpostos(cidade);

        System.out.println("Impostos arrecadados: " + valor);
        System.out.println("Florins atuais: " + cidade.getFlorins());
    }

    private void definirTaxaImposto() {

        System.out.print("Nova taxa de imposto (0 a 50): ");
        int taxa = lerOpcao();

        if (governo.definirTaxaImposto(cidade, taxa)) {
            System.out.println("Taxa atualizada!");
        } else {
            System.out.println("Taxa invalida!");
        }
    }

    private void definirJustica() {

        System.out.println("1 - Justa");
        System.out.println("2 - Moderada");
        System.out.println("3 - Severa");
        System.out.print("Escolha: ");

        String nivel;

        switch (lerOpcao()) {
            case 1:
                nivel = "Justa";
                break;
            case 2:
                nivel = "Moderada";
                break;
            case 3:
                nivel = "Severa";
                break;
            default:
                System.out.println("Opcao invalida!");
                return;
        }

        if (governo.definirJustica(cidade, nivel)) {
            System.out.println("Justica atualizada!");
        }
    }
}