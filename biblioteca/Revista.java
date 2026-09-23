/**
 * Outra subclasse de Item, com comportamento próprio.
 */
public class Revista extends Item {
    private int edicao;

    public Revista(String titulo, String autor, int ano, int edicao) {
        super(titulo, autor, ano);
        this.edicao = edicao;
        this.multaPorDia = 0.75;
    }

    public int getEdicao() { return edicao; }
    public void setEdicao(int edicao) { this.edicao = edicao; }

    // REESCRITA de método: revistas têm multa máxima de R$ 20,00
    @Override
    public double calcularMulta(int diasAtraso) {
        return Math.min(super.calcularMulta(diasAtraso), 20.00);
    }

    @Override
    public void exibirInformacoes() {
        super.exibirInformacoes();
        System.out.println("     Revista | Edição nº " + edicao);
    }
}
