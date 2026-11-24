package bolonhaemilano;

import java.util.Scanner;
import java.util.List;

public class Menu {

    private Scanner scanner = new Scanner(System.in);
    private boolean emExecucao = true;

    private Partida partida;
    private Cidade cidade;

    private Mercado mercado = new Mercado();
    private Agricultura agricultura = new Agricultura();
    private Governo governo = new Governo();
    private Construcoes construcoes = new Construcoes();
    private Nobreza nobreza = new Nobreza();

    public void executar() {

        while (emExecucao) {
            exibirOpcoes();
            processarOpcao(lerOpcao());
        }
    }

    private void exibirOpcoes() {

        System.out.println("\n=== BOLONHA E MILANO ===");

        if (partida != null) {

            System.out.println("Ano: " + partida.getAnoAtual());
            System.out.println("Cidade da vez: " + cidade.getNome());
            System.out.println("Jogador: " + cidade.getDono().getNome());
        }

        System.out.println("1 - Novo jogo");
        System.out.println("2 - Ver status da cidade");
        System.out.println("3 - Mercado");
        System.out.println("4 - Agricultura");
        System.out.println("5 - Governo");
        System.out.println("6 - Construcoes");
        System.out.println("7 - Encerrar turno");
        System.out.println("8 - Ver classificacao");
        System.out.println("9 - Sobre o jogo");
        System.out.println("0 - Sair");
        System.out.print("Escolha: ");
    }

    private int lerOpcao() {

        try {
            return Integer.parseInt(scanner.nextLine().trim());
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

            case 6:
                abrirConstrucoes();
                break;

            case 7:
                encerrarTurno();
                break;

            case 8:
                exibirClassificacao();
                break;

            case 9:
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

        partida = new Partida();

        String[] nomesCidades = {
            "Bolonha", "Milano", "Florenca", "Veneza"
        };

        System.out.print("Quantidade de jogadores (1 a 4): ");

        int quantidade = lerOpcao();

        if (quantidade < 1 || quantidade > 4) {
            quantidade = 2;
        }

        for (int i = 0; i < quantidade; i++) {

            System.out.print("Nome do jogador " + (i + 1) + ": ");

            String nome = scanner.nextLine();

            if (nome.trim().isEmpty()) {
                nome = "Jogador " + (i + 1);
            }

            Jogador jogador = new Jogador(nome);

            Cidade novaCidade =
                new Cidade(nomesCidades[i], jogador);

            partida.adicionarCidade(novaCidade);

            System.out.println(nome + " governa " + nomesCidades[i]);
        }

        cidade = partida.getCidadeAtual();

        System.out.println("Nova partida iniciada!");
        exibirStatus();
    }

    private void exibirStatus() {

        if (partida == null) {
            System.out.println("Nenhum jogo em andamento.");
            return;
        }

        cidade.exibirStatus();

        System.out.println("Prestigio: " +
            nobreza.calcularPrestigio(cidade));
    }

    private void exibirSobre() {

        System.out.println("Bolonha e Milano");
        System.out.println("Jogo de estrategia medieval.");
        System.out.println("De 1 a 4 jogadores.");
        System.out.println("Governe sua cidade e conquiste prestigio.");
        System.out.println("Venca tornando-se Rei/Rainha");
        System.out.println("ou liderando a classificacao ao fim de 1410.");
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

        if (partida == null) {
            System.out.println("Nenhum jogo em andamento.");
            return;
        }

        boolean noMercado = true;

        while (noMercado) {

            System.out.println("\n=== MERCADO ===");
            System.out.println("Florins: " + cidade.getFlorins());
            System.out.println("Graos: " + cidade.getGraos());
            System.out.println("Terras: " + cidade.getTerras());

            System.out.println("Preco do grao: " +
                mercado.getPrecoGrao());

            System.out.println("Preco da terra: " +
                mercado.getPrecoTerra());

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

        if (partida == null) {
            System.out.println("Nenhum jogo em andamento.");
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

            switch (lerOpcao()) {

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

        if (producao == -1) {
            System.out.println("A colheita deste ano ja foi realizada!");
            return;
        }

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

        if (partida == null) {
            System.out.println("Nenhum jogo em andamento.");
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

        if (valor == -1) {
            System.out.println("Os impostos deste ano ja foram arrecadados!");
            return;
        }

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

    private void abrirConstrucoes() {

        if (partida == null) {
            System.out.println("Nenhum jogo em andamento.");
            return;
        }

        boolean naConstrucoes = true;

        while (naConstrucoes) {

            System.out.println("\n=== CONSTRUCOES ===");
            System.out.println("Florins: " + cidade.getFlorins());
            System.out.println("Servos: " + cidade.getServos());
            System.out.println("Soldados: " + cidade.getSoldados());
            System.out.println("Construcoes: " + cidade.getConstrucoes());

            System.out.println("Preco do soldado: " +
                construcoes.getPrecoSoldado() + " florins + 1 servo");

            System.out.println("Preco da construcao: " +
                construcoes.getPrecoConstrucao() + " florins");

            System.out.println("1 - Recrutar soldados");
            System.out.println("2 - Construir");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");

            switch (lerOpcao()) {

                case 1:
                    recrutarSoldados();
                    break;

                case 2:
                    construir();
                    break;

                case 0:
                    naConstrucoes = false;
                    break;

                default:
                    System.out.println("Opcao invalida!");
            }
        }
    }

    private void recrutarSoldados() {

        int qtd = lerQuantidade();

        if (qtd == 0) {
            return;
        }

        if (construcoes.recrutarSoldados(cidade, qtd)) {
            System.out.println("Soldados recrutados com sucesso!");
        } else {
            System.out.println("Recursos insuficientes!");
        }
    }

    private void construir() {

        int qtd = lerQuantidade();

        if (qtd == 0) {
            return;
        }

        if (construcoes.construir(cidade, qtd)) {
            System.out.println("Construcoes realizadas com sucesso!");
        } else {
            System.out.println("Recursos insuficientes!");
        }
    }

    private void encerrarTurno() {

        if (partida == null) {
            System.out.println("Nenhum jogo em andamento.");
            return;
        }

        boolean fimDoAno = partida.passarTurno();

        if (fimDoAno) {

            System.out.println("\n=== FIM DO ANO ===");

            List<Cidade> promovidas =
                partida.encerrarAno(nobreza);

            for (Cidade promovida : promovidas) {

                System.out.println(
                    promovida.getDono().getNome() +
                    " foi promovido para " +
                    promovida.getDono().getTitulo()
                );
            }

            exibirClassificacao();

            if (partida.terminou()) {
                encerrarPartida();
                return;
            }

            System.out.println("Novo ano: " + partida.getAnoAtual());
        }

        cidade = partida.getCidadeAtual();

        System.out.println("Agora e a vez de " +
            cidade.getDono().getNome());

        System.out.println("Cidade: " + cidade.getNome());
    }

    private void exibirClassificacao() {

        if (partida == null) {
            System.out.println("Nenhum jogo em andamento.");
            return;
        }

        System.out.println("\n=== CLASSIFICACAO ===");

        List<Cidade> ranking = partida.getCidades();

        ranking.sort((a, b) -> {

            int comparacao = Integer.compare(
                b.getDono().getNivelTitulo(),
                a.getDono().getNivelTitulo()
            );

            if (comparacao != 0) {
                return comparacao;
            }

            return Integer.compare(
                nobreza.calcularPrestigio(b),
                nobreza.calcularPrestigio(a)
            );
        });

        int posicao = 1;

        for (Cidade c : ranking) {

            int prestigio = nobreza.calcularPrestigio(c);

            int necessario =
                nobreza.prestigioParaProximoTitulo(c.getDono());

            System.out.println("\n" + posicao + " - " + c.getNome());
            System.out.println("Jogador: " + c.getDono().getNome());
            System.out.println("Titulo: " + c.getDono().getTitulo());
            System.out.println("Prestigio: " + prestigio);

            if (necessario != -1) {

                System.out.println("Faltam " +
                    Math.max(0, necessario - prestigio) +
                    " pontos para o proximo titulo.");

            } else {
                System.out.println("Titulo maximo alcancado!");
            }

            posicao++;
        }
    }

    private void encerrarPartida() {

        Cidade vencedora =
            partida.determinarVencedor(nobreza);

        System.out.println("\n=== FIM DA PARTIDA ===");

        if (vencedora != null) {

            System.out.println("Vencedor: " +
                vencedora.getDono().getNome());

            System.out.println("Cidade: " + vencedora.getNome());

            System.out.println("Titulo: " +
                vencedora.getDono().getTitulo());

            System.out.println("Prestigio: " +
                nobreza.calcularPrestigio(vencedora));
        }

        partida = null;
        cidade = null;
    }
}