# P2 - Sistema CRUD de Login

Avaliação P2 da disciplina **Desenvolvimento para Servidores I**.

- **Aluno:** SEU NOME AQUI
- **Professor:** Marcos Costa de Sousa
- **Instituição:** Fatec São Roque

Aplicação desktop em **Java (Swing)** com banco de dados **MySQL**, que implementa o CRUD completo de um usuário, seguindo os passos feitos em aula.

---

## Funcionalidades

| Operação | Tela | O que faz |
|----------|------|-----------|
| **Create** | Cadastro | Cadastra um novo usuário, validando campos vazios, senhas diferentes e usuário duplicado |
| **Read** | Login | Valida usuário e senha no banco de dados |
| **Update** | Alteração | Altera nome e senha do usuário logado (exige a senha atual) |
| **Delete** | Menu | Exclui o usuário logado, com confirmação |

Fluxo do sistema:

```
Login ──► Menu (Bem vindo, <nome>) ──► Alterar Dados ──► volta ao Login
  │              │
  │              └──► Excluir ──► volta ao Login
  └──► Cadastro ──► volta ao Login
```

## Tecnologias

- Java 21
- Java Swing (interface gráfica)
- JDBC com MySQL Connector/J 9.6.0
- MySQL 8
- Maven
- Apache NetBeans

## Estrutura do projeto

```
P2Login/
├── pom.xml
├── sql/
│   └── QueryInicial.sql        # criação do banco e da tabela
└── src/main/java/Login/
    ├── Conexao.java            # conexão com o banco
    ├── Usuario.java            # regras de negócio e CRUD
    ├── TelaLogin.java          # tela de login
    ├── TelaCadastro.java       # tela de cadastro
    ├── TelaInicio.java         # menu (alterar / excluir / voltar)
    ├── TelaAlteracao.java      # tela de alteração de dados
    ├── TestaConexao.java       # testa a conexão com o banco
    └── TestaTela.java          # inicia o sistema
```

## Banco de dados

Banco `bd_Login`, com uma única tabela:

| Campo | Tipo | Observação |
|-------|------|------------|
| `usuario` | varchar(10) | chave primária |
| `nome` | varchar(30) | |
| `senha` | char(32) | senha criptografada com **MD5** |

## Como executar

### 1. Pré-requisitos
- JDK 21 ou superior
- MySQL 8 em execução
- Maven (já incluso no NetBeans)

### 2. Criar o banco de dados
Execute o script no MySQL:

```bash
mysql -u root -p < sql/QueryInicial.sql
```

### 3. Criar o usuário do banco (Linux)
No Linux, o Java normalmente não consegue autenticar com o `root` do MySQL. Crie um usuário para o sistema:

```sql
CREATE USER 'aluno'@'localhost' IDENTIFIED BY '123456';
GRANT ALL PRIVILEGES ON bd_Login.* TO 'aluno'@'localhost';
FLUSH PRIVILEGES;
```

Por padrão, o sistema se conecta com `aluno` / `123456`. Para usar outro usuário, defina as variáveis de ambiente antes de executar:

```bash
export DB_USER=seu_usuario
export DB_PASSWORD=sua_senha
```

### 4. Abrir e executar
1. Abra a pasta do projeto no NetBeans (**File → Open Project**).
2. Execute `TestaConexao.java` para testar a conexão. O console deve mostrar *"Conexão aberta com sucesso"*.
3. Execute `TestaTela.java` para abrir o sistema.

### 5. Usuário de teste
| Usuário | Senha |
|---------|-------|
| `teste` | `123` |

> **Atenção (Linux):** o MySQL diferencia maiúsculas de minúsculas nos nomes de tabela. A tabela deve se chamar `usuario`, em minúsculo, como no script.

## Segurança

- As senhas são gravadas com `MD5` (função do próprio MySQL), nunca em texto puro.
- As consultas usam `PreparedStatement`, o que protege contra SQL Injection.
- Usuário e senha do banco podem ser trocados por variáveis de ambiente (`DB_USER` e `DB_PASSWORD`).

## Roteiro de teste

1. Clicar em **Entrar** com os campos vazios e ver o aviso.
2. Em **Cadastrar**, testar campos vazios e senhas diferentes, e depois cadastrar um usuário.
3. Entrar com o usuário criado e ver a mensagem de boas-vindas.
4. Em **Alterar Dados**, mudar o nome e a senha, e entrar novamente.
5. Em **Excluir**, confirmar a exclusão e tentar entrar de novo (o acesso deve ser negado).
6. Conferir no banco: `SELECT * FROM bd_Login.usuario;`

## Autor

**SEU NOME AQUI** — Fatec São Roque, 2026.
