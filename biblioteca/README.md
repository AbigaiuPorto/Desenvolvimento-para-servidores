# Sistema de Biblioteca (POO em Java)

Projeto da P1 de Programação Orientada a Objetos.

## Conceitos aplicados
| Conceito | Onde |
|---|---|
| Classe / Atributos / Visibilidade | `private`, `protected`, `public` em todas as classes |
| Método construtor | `Item`, `Livro`, `Revista`, `Usuario`, `Biblioteca` |
| Encapsulamento | atributos privados; `Usuario.getItensEmprestados()` devolve cópia |
| Getters e Setters | com validação em `Item` e `Livro` |
| Herança | `Livro` e `Revista` estendem `Item` |
| Sobrecarga de método | `Item.calcularMulta(int)` / `(int, double)`; `Biblioteca.buscar(String)` / `(int)`; construtores de `Item` |
| Reescrita de método | `calcularMulta` e `exibirInformacoes` em `Livro` e `Revista` |

## Como executar
```bash
cd src
javac *.java
java Main
```
