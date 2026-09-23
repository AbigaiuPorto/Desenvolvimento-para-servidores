import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nome;
    private String matricula;
    private List<Item> itensEmprestados;

    public static final int LIMITE_EMPRESTIMOS = 3;

    public Usuario(String nome, String matricula) {
        this.nome = nome;
        this.matricula = matricula;
        this.itensEmprestados = new ArrayList<>();
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getMatricula() { return matricula; }

    // Retorna cópia para não expor a lista interna (encapsulamento)
    public List<Item> getItensEmprestados() {
        return new ArrayList<>(itensEmprestados);
    }

    public boolean podeEmprestar() {
        return itensEmprestados.size() < LIMITE_EMPRESTIMOS;
    }

    void adicionarItem(Item item) { itensEmprestados.add(item); }
    void removerItem(Item item) { itensEmprestados.remove(item); }
}
