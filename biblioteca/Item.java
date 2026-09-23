/**
 * Classe base do sistema. Representa qualquer item do acervo.
 * Conceitos: classe, atributos com visibilidade, encapsulamento,
 * construtores (sobrecarga), getters/setters e sobrecarga de método.
 */
public class Item {
    // Atributos privados: só acessíveis via getters/setters (encapsulamento)
    private int id;
    private String titulo;
    private String autor;
    private int anoPublicacao;
    private boolean disponivel;

    // Atributo protegido: acessível pelas subclasses (herança)
    protected double multaPorDia;

    // Atributo estático privado, usado para gerar IDs automáticos
    private static int proximoId = 1;

    // Construtor completo
    public Item(String titulo, String autor, int anoPublicacao) {
        this.id = proximoId++;
        setTitulo(titulo);
        setAutor(autor);
        setAnoPublicacao(anoPublicacao);
        this.disponivel = true;
        this.multaPorDia = 1.00;
    }

    // SOBRECARGA de construtor: sem o ano de publicação
    public Item(String titulo, String autor) {
        this(titulo, autor, 0);
    }

    // ===== Getters e Setters =====
    public int getId() { return id; }

    public String getTitulo() { return titulo; }

    public void setTitulo(String titulo) {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("O título não pode ser vazio.");
        }
        this.titulo = titulo;
    }

    public String getAutor() { return autor; }

    public void setAutor(String autor) {
        if (autor == null || autor.trim().isEmpty()) {
            throw new IllegalArgumentException("O autor não pode ser vazio.");
        }
        this.autor = autor;
    }

    public int getAnoPublicacao() { return anoPublicacao; }

    public void setAnoPublicacao(int anoPublicacao) {
        if (anoPublicacao < 0) {
            throw new IllegalArgumentException("Ano inválido.");
        }
        this.anoPublicacao = anoPublicacao;
    }

    public boolean isDisponivel() { return disponivel; }

    // ===== Métodos de negócio =====
    public boolean emprestar() {
        if (!disponivel) {
            return false;
        }
        disponivel = false;
        return true;
    }

    public void devolver() {
        disponivel = true;
    }

    // SOBRECARGA de método: mesma assinatura de nome, parâmetros diferentes
    public double calcularMulta(int diasAtraso) {
        if (diasAtraso <= 0) {
            return 0;
        }
        return diasAtraso * multaPorDia;
    }

    public double calcularMulta(int diasAtraso, double desconto) {
        double multa = calcularMulta(diasAtraso);
        return multa - (multa * desconto / 100.0);
    }

    public void exibirInformacoes() {
        System.out.println("[" + id + "] " + titulo + " - " + autor
                + (anoPublicacao > 0 ? " (" + anoPublicacao + ")" : "")
                + " | " + (disponivel ? "Disponível" : "Emprestado"));
    }
}
