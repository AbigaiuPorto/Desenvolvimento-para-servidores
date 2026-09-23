public class Main {
    public static void main(String[] args) {
        Biblioteca bib = new Biblioteca("Biblioteca Central");

        bib.cadastrarItem(new Livro("Dom Casmurro", "Machado de Assis", 1899, "978-85-359-0277-5", 256));
        bib.cadastrarItem(new Livro("Clean Code", "Robert C. Martin", 2008, "978-0-13-235088-4", 464));
        bib.cadastrarItem(new Revista("Superinteressante", "Editora Abril", 2024, 450));
        bib.cadastrarItem(new Item("Manuscrito antigo", "Desconhecido")); // construtor sobrecarregado

        Usuario ana = new Usuario("Ana", "2026001");
        Usuario bruno = new Usuario("Bruno", "2026002");
        bib.cadastrarUsuario(ana);
        bib.cadastrarUsuario(bruno);

        bib.listarAcervo();

        System.out.println("\n=== Empréstimos ===");
        Item dom = bib.buscar("Dom Casmurro"); // busca por título
        Item clean = bib.buscar(2);            // busca por id
        bib.emprestar(ana, dom);
        bib.emprestar(bruno, dom);             // já emprestado
        bib.emprestar(bruno, clean);

        System.out.println("\n=== Devoluções e multas (reescrita de método) ===");
        bib.devolver(ana, dom, 12);            // Livro: 12*1,50 + 5,00
        Item revista = bib.buscar("Superinteressante");
        bib.emprestar(ana, revista);
        bib.devolver(ana, revista, 60);        // Revista: limitada a R$ 20,00

        System.out.println("\n=== Sobrecarga de calcularMulta ===");
        System.out.printf("Multa 5 dias: R$ %.2f%n", clean.calcularMulta(5));
        System.out.printf("Multa 5 dias com 20%% desconto: R$ %.2f%n", clean.calcularMulta(5, 20));

        bib.listarAcervo();
    }
}
