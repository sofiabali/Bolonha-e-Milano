package bolonhaemilano;

public class Mercado {

    private static final int PRECO_GRAO = 2;
    private static final int PRECO_TERRA = 1;

    public int getPrecoGrao() {
        return PRECO_GRAO;
    }

    public int getPrecoTerra() {
        return PRECO_TERRA;
    }

    public boolean comprarGraos(Cidade cidade, int qtd) {
        if (qtd <= 0 || qtd > cidade.getFlorins() / PRECO_GRAO
                || qtd > Integer.MAX_VALUE - cidade.getGraos()) {
            return false;
        }

        int custo = qtd * PRECO_GRAO;

        if (!cidade.removerFlorins(custo)) {
            return false;
        }

        cidade.adicionarGraos(qtd);
        return true;
    }

    public boolean venderGraos(Cidade cidade, int qtd) {
        if (qtd <= 0 || qtd > (Integer.MAX_VALUE - cidade.getFlorins()) / PRECO_GRAO) {
            return false;
        }

        if (!cidade.removerGraos(qtd)) {
            return false;
        }

        cidade.adicionarFlorins(qtd * PRECO_GRAO);
        return true;
    }

    public boolean comprarTerras(Cidade cidade, int qtd) {
        if (qtd <= 0 || qtd > Integer.MAX_VALUE - cidade.getTerras()) {
            return false;
        }

        int custo = qtd * PRECO_TERRA;

        if (!cidade.removerFlorins(custo)) {
            return false;
        }

        cidade.adicionarTerras(qtd);
        return true;
    }

    public boolean venderTerras(Cidade cidade, int qtd) {
        if (qtd <= 0 || qtd > Integer.MAX_VALUE - cidade.getFlorins()) {
            return false;
        }

        if (!cidade.removerTerras(qtd)) {
            return false;
        }

        cidade.adicionarFlorins(qtd * PRECO_TERRA);
        return true;
    }
}