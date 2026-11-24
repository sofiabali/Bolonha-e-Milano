package bolonhaemilano;

import java.util.ArrayList;
import java.util.List;

public class Partida {

    public static final int MAXIMO_JOGADORES = 4;
    private static final int ANO_FINAL = 1410;

    private List<Cidade> cidades = new ArrayList<>();
    private int indiceAtual = 0;

    public boolean adicionarCidade(Cidade cidade) {

        if (cidade == null || cidades.size() >= MAXIMO_JOGADORES) {
            return false;
        }

        cidades.add(cidade);
        return true;
    }

    public List<Cidade> getCidades() {
        return new ArrayList<>(cidades);
    }

    public Cidade getCidadeAtual() {

        if (cidades.isEmpty()) {
            return null;
        }

        return cidades.get(indiceAtual);
    }

    public int getAnoAtual() {

        if (cidades.isEmpty()) {
            return 1400;
        }

        return cidades.get(0).getAnoAtual();
    }

    public int getAnoFinal() {
        return ANO_FINAL;
    }

    public boolean passarTurno() {

        if (cidades.isEmpty()) {
            return false;
        }

        indiceAtual++;

        if (indiceAtual >= cidades.size()) {
            indiceAtual = 0;
            return true;
        }

        return false;
    }

    public List<Cidade> encerrarAno(Nobreza nobreza) {

        List<Cidade> promovidas = new ArrayList<>();

        for (Cidade cidade : cidades) {

            cidade.avancarAno();

            if (nobreza.verificarPromocao(cidade)) {
                promovidas.add(cidade);
            }
        }

        return promovidas;
    }

    public boolean terminou() {

        if (getAnoAtual() > ANO_FINAL) {
            return true;
        }

        for (Cidade cidade : cidades) {

            if (cidade.getDono().ehMonarca()) {
                return true;
            }
        }

        return false;
    }

    public Cidade determinarVencedor(Nobreza nobreza) {

        Cidade vencedora = null;

        for (Cidade cidade : cidades) {

            if (vencedora == null) {
                vencedora = cidade;
                continue;
            }

            int nivel = cidade.getDono().getNivelTitulo();
            int nivelVencedora = vencedora.getDono().getNivelTitulo();

            if (nivel > nivelVencedora) {
                vencedora = cidade;

            } else if (nivel == nivelVencedora &&
                       nobreza.calcularPrestigio(cidade) >
                       nobreza.calcularPrestigio(vencedora)) {

                vencedora = cidade;
            }
        }

        return vencedora;
    }
}