package bolonhaemilano;

public class Testes {

    private static int aprovados = 0;
    private static int reprovados = 0;

    public static void main(String[] args) {

        System.out.println("===== TESTES BOLONHA E MILANO =====");

        testarCidade();
        testarMercado();
        testarAgricultura();
        testarGoverno();
        testarConstrucoes();
        testarJogadorENobreza();
        testarPartida();

        System.out.println("\n==============================");
        System.out.println("Aprovados: " + aprovados +
                           " | Reprovados: " + reprovados);

        if (reprovados > 0) {
            System.exit(1);
        }
    }

    private static void verificar(String descricao, boolean condicao) {

        if (condicao) {
            aprovados++;
            System.out.println("[OK] " + descricao);
        } else {
            reprovados++;
            System.out.println("[FALHA] " + descricao);
        }
    }

    private static Cidade novaCidade() {

        return new Cidade("Bolonha", new Jogador("Teste"));
    }

    private static void testarCidade() {

        System.out.println("\n-- Cidade --");

        Cidade cidade = novaCidade();

        verificar("Cidade nasce com 1000 florins",
                cidade.getFlorins() == 1000);

        verificar("Cidade nasce no ano 1400",
                cidade.getAnoAtual() == 1400);

        verificar("Remover florins acima do saldo e recusado",
                !cidade.removerFlorins(2000));

        verificar("Saldo permanece igual",
                cidade.getFlorins() == 1000);

        cidade.marcarColheitaFeita();
        cidade.avancarAno();

        verificar("Ano avancou para 1401",
                cidade.getAnoAtual() == 1401);

        verificar("Novo ano libera a colheita",
                !cidade.isColheitaFeita());
    }

    private static void testarMercado() {

        System.out.println("\n-- Mercado --");

        Cidade cidade = novaCidade();
        Mercado mercado = new Mercado();

        verificar("Comprar 100 graos funciona",
                mercado.comprarGraos(cidade, 100));

        verificar("Compra custa 200 florins",
                cidade.getFlorins() == 800);

        verificar("Compra adiciona 100 graos",
                cidade.getGraos() == 600);

        verificar("Quantidade negativa e recusada",
                !mercado.comprarGraos(cidade, -10));

        verificar("Compra sem saldo e recusada",
                !mercado.comprarGraos(cidade, 1000));
    }

    private static void testarAgricultura() {

        System.out.println("\n-- Agricultura --");

        Cidade cidade = novaCidade();
        Agricultura agricultura = new Agricultura();

        int producao = agricultura.processarColheita(cidade);

        verificar("Primeira colheita produz 2000 graos",
                producao == 2000);

        verificar("Estoque final possui 2000 graos",
                cidade.getGraos() == 2000);

        verificar("Segunda colheita no mesmo ano e recusada",
                agricultura.processarColheita(cidade) == -1);

        verificar("Distribuir 10% dos alimentos e recusado",
                !agricultura.distribuirAlimentos(cidade, 10));
    }

    private static void testarGoverno() {

        System.out.println("\n-- Governo --");

        Cidade cidade = novaCidade();
        Governo governo = new Governo();

        verificar("Taxa de 60% e recusada",
                !governo.definirTaxaImposto(cidade, 60));

        verificar("Justica Branda e recusada",
                !governo.definirJustica(cidade, "Branda"));

        governo.definirTaxaImposto(cidade, 10);
        governo.definirJustica(cidade, "Severa");

        verificar("Justica Severa arrecada 26 florins",
                governo.arrecadarImpostos(cidade) == 26);

        verificar("Segunda arrecadacao no ano e recusada",
                governo.arrecadarImpostos(cidade) == -1);
    }

    private static void testarConstrucoes() {

        System.out.println("\n-- Construcoes --");

        Cidade cidade = novaCidade();
        Construcoes construcoes = new Construcoes();

        cidade.removerServos(150);

        verificar("Recrutar sem servos suficientes e recusado",
                !construcoes.recrutarSoldados(cidade, 100));

        verificar("Recrutamento recusado nao debita florins",
                cidade.getFlorins() == 1000);

        verificar("Construir 10 funciona",
                construcoes.construir(cidade, 10));

        verificar("Construir 10 custa 200 florins",
                cidade.getFlorins() == 800);
    }

    private static void testarJogadorENobreza() {

        System.out.println("\n-- Jogador e Nobreza --");

        Cidade cidade = novaCidade();
        Jogador jogador = cidade.getDono();
        Nobreza nobreza = new Nobreza();

        verificar("Jogador comeca como Cavaleiro",
                jogador.getTitulo().equals("Cavaleiro"));

        verificar("Prestigio inicial e 300",
                nobreza.calcularPrestigio(cidade) == 300);

        verificar("Prestigio 300 nao promove",
                !nobreza.verificarPromocao(cidade));

        cidade.adicionarConstrucoes(50);

        verificar("50 construcoes permitem promocao",
                nobreza.verificarPromocao(cidade));

        verificar("Promove somente um titulo por chamada",
                jogador.getNivelTitulo() == 1);

        while (!jogador.ehMonarca()) {
            jogador.promover();
        }

        verificar("Promocao alem de Rei/Rainha e recusada",
                !jogador.promover());
    }

    private static void testarPartida() {

        System.out.println("\n-- Partida --");

        Partida partida = new Partida();
        Nobreza nobreza = new Nobreza();

        Cidade bolonha = new Cidade(
                "Bolonha", new Jogador("Jogador 1"));

        Cidade milano = new Cidade(
                "Milano", new Jogador("Jogador 2"));

        partida.adicionarCidade(bolonha);
        partida.adicionarCidade(milano);

        verificar("Primeiro turno pertence a Bolonha",
                partida.getCidadeAtual() == bolonha);

        verificar("Turno de Bolonha nao fecha o ano",
                !partida.passarTurno());

        verificar("Agora e a vez de Milano",
                partida.getCidadeAtual() == milano);

        verificar("Turno de Milano fecha o ano",
                partida.passarTurno());

        milano.adicionarConstrucoes(50);

        partida.encerrarAno(nobreza);

        verificar("Todas as cidades avancaram para 1401",
                bolonha.getAnoAtual() == 1401 &&
                milano.getAnoAtual() == 1401);

        verificar("Partida ainda nao terminou",
                !partida.terminou());

        verificar("Vencedor parcial e Milano",
                partida.determinarVencedor(nobreza) == milano);

        partida.adicionarCidade(
                new Cidade("Florenca", new Jogador("Jogador 3")));

        partida.adicionarCidade(
                new Cidade("Veneza", new Jogador("Jogador 4")));

        verificar("Quinta cidade e recusada",
                !partida.adicionarCidade(
                        new Cidade("Roma", new Jogador("Jogador 5"))));
    }
}