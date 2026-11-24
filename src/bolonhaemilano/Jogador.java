package bolonhaemilano;

public class Jogador {

    private static final String[] TITULOS = {
        "Cavaleiro", "Barao", "Conde",
        "Marques", "Duque", "Rei/Rainha"
    };

    private String nome;
    private int nivelTitulo = 0;

    public Jogador(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public String getTitulo() {
        return TITULOS[nivelTitulo];
    }

    public int getNivelTitulo() {
        return nivelTitulo;
    }

    public boolean promover() {
        if (ehMonarca()) {
            return false;
        }

        nivelTitulo++;
        return true;
    }

    public boolean ehMonarca() {
        return nivelTitulo == TITULOS.length - 1;
    }
}