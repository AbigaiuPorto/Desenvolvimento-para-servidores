/**
 * Subclasse de Item (herança).
 * Reescreve (override) calcularMulta e exibirInformacoes.
 */
public class Livro extends Item {
    private String isbn;
    private int numeroPaginas;

    public Livro(String titulo, String autor, int ano, String isbn, int numeroPaginas) {
        super(titulo, autor, ano); // chama o construtor da superclasse
        this.isbn = isbn;
        setNumeroPaginas(numeroPaginas);
        this.multaPorDia = 1.50;   // atributo protected herdado
    }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public int getNumeroPaginas() { return numeroPaginas; }

    public void setNumeroPaginas(int numeroPaginas) {
        if (numeroPaginas <= 0) {
            throw new IllegalArgumentException("Número de páginas deve ser positivo.");
        }
        this.numeroPaginas = numeroPaginas;
    }

    // REESCRITA de método: livros têm multa extra após 10 dias de atraso
    @Override
    public double calcularMulta(int diasAtraso) {
        double multa = super.calcularMulta(diasAtraso);
        if (diasAtraso > 10) {
            multa += 5.00;
        }
        return multa;
    }

    @Override
    public void exibirInformacoes() {
        super.exibirInformacoes();
        System.out.println("     Livro | ISBN: " + isbn + " | " + numeroPaginas + " páginas");
    }
}
