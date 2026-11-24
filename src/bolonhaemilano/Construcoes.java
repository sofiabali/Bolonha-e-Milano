package bolonhaemilano;

public class Construcoes {

    private static final int PRECO_SOLDADO = 5;
    private static final int SERVOS_POR_SOLDADO = 1;
    private static final int PRECO_CONSTRUCAO = 20;

    public int getPrecoSoldado() {
        return PRECO_SOLDADO;
    }

    public int getPrecoConstrucao() {
        return PRECO_CONSTRUCAO;
    }

    public boolean recrutarSoldados(Cidade cidade, int qtd) {

        if (qtd <= 0 ||
            qtd > cidade.getFlorins() / PRECO_SOLDADO ||
            qtd > cidade.getServos() / SERVOS_POR_SOLDADO ||
            qtd > Integer.MAX_VALUE - cidade.getSoldados()) {
            return false;
        }

        int custo = qtd * PRECO_SOLDADO;
        int servosNecessarios = qtd * SERVOS_POR_SOLDADO;

        cidade.removerFlorins(custo);
        cidade.removerServos(servosNecessarios);
        cidade.adicionarSoldados(qtd);

        return true;
    }

    public boolean construir(Cidade cidade, int qtd) {

        if (qtd <= 0 ||
            qtd > cidade.getFlorins() / PRECO_CONSTRUCAO ||
            qtd > Integer.MAX_VALUE - cidade.getConstrucoes()) {
            return false;
        }

        int custo = qtd * PRECO_CONSTRUCAO;

        if (!cidade.removerFlorins(custo)) {
            return false;
        }

        cidade.adicionarConstrucoes(qtd);

        return true;
    }
}