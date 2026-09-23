import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private String nome;
    private List<Item> acervo = new ArrayList<>();
    private List<Usuario> usuarios = new ArrayList<>();

    public Biblioteca(String nome) {
        this.nome = nome;
    }

    public String getNome() { return nome; }

    public void cadastrarItem(Item item) { acervo.add(item); }

    public void cadastrarUsuario(Usuario usuario) { usuarios.add(usuario); }

    // SOBRECARGA de método: buscar por título ou por id
    public Item buscar(String titulo) {
        for (Item i : acervo) {
            if (i.getTitulo().equalsIgnoreCase(titulo)) {
                return i;
            }
        }
        return null;
    }

    public Item buscar(int id) {
        for (Item i : acervo) {
            if (i.getId() == id) {
                return i;
            }
        }
        return null;
    }

    public boolean emprestar(Usuario usuario, Item item) {
        if (!usuario.podeEmprestar()) {
            System.out.println("❌ " + usuario.getNome() + " atingiu o limite de empréstimos.");
            return false;
        }
        if (!item.emprestar()) {
            System.out.println("❌ \"" + item.getTitulo() + "\" já está emprestado.");
            return false;
        }
        usuario.adicionarItem(item);
        System.out.println("✅ " + usuario.getNome() + " pegou \"" + item.getTitulo() + "\".");
        return true;
    }

    public void devolver(Usuario usuario, Item item, int diasAtraso) {
        item.devolver();
        usuario.removerItem(item);
        double multa = item.calcularMulta(diasAtraso);
        System.out.printf("↩️  \"%s\" devolvido por %s. Multa: R$ %.2f%n",
                item.getTitulo(), usuario.getNome(), multa);
    }

    public void listarAcervo() {
        System.out.println("\n=== Acervo da " + nome + " ===");
        for (Item i : acervo) {
            i.exibirInformacoes(); // POLIMORFISMO: chama a versão de cada subclasse
        }
    }
}
