package bolonhaemilano;

public class Cidade {

    private String nome;
    private int anoAtual = 1400;
    private Jogador dono;

    private int florins = 1000;
    private int terras = 10000;
    private int graos = 500;
    private int servos = 200;

    private int taxaImposto = 10;
    private String nivelJustica = "Moderada";

    private int soldados = 0;
    private int construcoes = 0;

    public Cidade(String nome, Jogador dono) {
        this.nome = nome;
        this.dono = dono;
    }

    public String getNome() {
        return nome;
    }

    public int getAnoAtual() {
        return anoAtual;
    }

    public Jogador getDono() {
        return dono;
    }

    public int getFlorins() {
        return florins;
    }

    public int getTerras() {
        return terras;
    }

    public int getGraos() {
        return graos;
    }

    public int getServos() {
        return servos;
    }

    public int getSoldados() {
        return soldados;
    }

    public int getConstrucoes() {
        return construcoes;
    }

    public int getTaxaImposto() {
        return taxaImposto;
    }

    public void setTaxaImposto(int taxaImposto) {
        this.taxaImposto = taxaImposto;
    }

    public String getNivelJustica() {
        return nivelJustica;
    }

    public void setNivelJustica(String nivelJustica) {
        this.nivelJustica = nivelJustica;
    }

    public void avancarAno() {
        anoAtual++;
    }

    public void adicionarFlorins(int qtd) {
        if (qtd > 0) {
            florins += qtd;
        }
    }

    public boolean removerFlorins(int qtd) {
        if (qtd < 0 || qtd > florins) {
            return false;
        }
        florins -= qtd;
        return true;
    }

    public void adicionarTerras(int qtd) {
        if (qtd > 0) {
            terras += qtd;
        }
    }

    public boolean removerTerras(int qtd) {
        if (qtd < 0 || qtd > terras) {
            return false;
        }
        terras -= qtd;
        return true;
    }

    public void adicionarGraos(int qtd) {
        if (qtd > 0) {
            graos += qtd;
        }
    }

    public boolean removerGraos(int qtd) {
        if (qtd < 0 || qtd > graos) {
            return false;
        }
        graos -= qtd;
        return true;
    }

    public void adicionarServos(int qtd) {
        if (qtd > 0) {
            servos += qtd;
        }
    }

    public boolean removerServos(int qtd) {
        if (qtd < 0 || qtd > servos) {
            return false;
        }
        servos -= qtd;
        return true;
    }

    public void adicionarSoldados(int qtd) {
        if (qtd > 0) {
            soldados += qtd;
        }
    }

    public boolean removerSoldados(int qtd) {
        if (qtd < 0 || qtd > soldados) {
            return false;
        }
        soldados -= qtd;
        return true;
    }

    public void adicionarConstrucoes(int qtd) {
        if (qtd > 0) {
            construcoes += qtd;
        }
    }

    public boolean removerConstrucoes(int qtd) {
        if (qtd < 0 || qtd > construcoes) {
            return false;
        }
        construcoes -= qtd;
        return true;
    }

    public void exibirStatus() {
        System.out.println("\n=== STATUS DA CIDADE ===");
        System.out.println("Cidade: " + nome);
        System.out.println("Ano: " + anoAtual);
        System.out.println("Governante: " + dono.getNome());
        System.out.println("Titulo: " + dono.getTitulo());
        System.out.println("Florins: " + florins);
        System.out.println("Terras: " + terras + " hectares");
        System.out.println("Graos: " + graos);
        System.out.println("Servos: " + servos);
        System.out.println("Impostos: " + taxaImposto + "%");
        System.out.println("Justica: " + nivelJustica);
        System.out.println("Soldados: " + soldados);
        System.out.println("Construcoes: " + construcoes);
    }
}