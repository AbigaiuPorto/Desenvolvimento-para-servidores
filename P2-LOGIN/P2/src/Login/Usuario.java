package Login;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Usuario {
    private String usuario;
    private String nome;
    private String senha;

    private boolean resultUsuario;
    private boolean resultCadastro;
    private boolean resultAlteracao;
    private boolean resultExclusao;

    // Atributos Estáticos do sistema
    static String nomeUsuario;
    static String usuarioSistema;

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    // READ - valida usuário e senha (senha comparada com md5)
    public boolean verificaUsuario(String usuario, String senha) {
        Conexao banco = new Conexao();
        resultUsuario = false;
        try {
            banco.abrirConexao();
            PreparedStatement ps = banco.con.prepareStatement(
                    "SELECT * FROM usuario WHERE usuario = ? AND senha = md5(?)");
            ps.setString(1, usuario);
            ps.setString(2, senha);
            banco.resultset = ps.executeQuery();
            if (banco.resultset.next()) {
                resultUsuario = true;
                setUsuario(banco.resultset.getString(1));
                setNome(banco.resultset.getString(2));

                nomeUsuario = this.getNome();
                usuarioSistema = this.getUsuario();
            }
        } catch (SQLException | NullPointerException ec) {
            System.out.println("Erro ao consultar usuário " + ec.getMessage());
        }
        banco.fecharConexao();
        return resultUsuario;
    }

    // Sobrecarga do método verificaUsuario (apenas por usuário)
    public boolean verificaUsuario(String usuario) {
        Conexao banco = new Conexao();
        resultUsuario = false;
        try {
            banco.abrirConexao();
            PreparedStatement ps = banco.con.prepareStatement(
                    "SELECT * FROM usuario WHERE usuario = ?");
            ps.setString(1, usuario);
            banco.resultset = ps.executeQuery();
            resultUsuario = banco.resultset.next();
        } catch (SQLException | NullPointerException ec) {
            System.out.println("Erro ao consultar usuário " + ec.getMessage());
        }
        banco.fecharConexao();
        return resultUsuario;
    }

    // CREATE
    public boolean cadastraUsuario(String nome, String usuario, String senha) {
        Conexao banco = new Conexao();
        resultCadastro = false;
        try {
            banco.abrirConexao();
            PreparedStatement ps = banco.con.prepareStatement(
                    "INSERT INTO usuario (nome, usuario, senha) VALUES (?, ?, md5(?))");
            ps.setString(1, nome);
            ps.setString(2, usuario);
            ps.setString(3, senha);
            ps.executeUpdate();
            resultCadastro = true;
        } catch (SQLException | NullPointerException ec) {
            System.out.println("Erro ao inserir o usuário " + ec.getMessage());
        }
        banco.fecharConexao();
        return resultCadastro;
    }

    // UPDATE
    public boolean alteraUsuario(String nome, String usuario, String senha) {
        Conexao banco = new Conexao();
        resultAlteracao = false;
        try {
            banco.abrirConexao();
            PreparedStatement ps = banco.con.prepareStatement(
                    "UPDATE usuario SET nome = ?, senha = md5(?) WHERE usuario = ?");
            ps.setString(1, nome);
            ps.setString(2, senha);
            ps.setString(3, usuario);
            ps.executeUpdate();
            resultAlteracao = true;
        } catch (SQLException | NullPointerException ec) {
            System.out.println("Erro ao atualizar usuário " + ec.getMessage());
        }
        banco.fecharConexao();
        return resultAlteracao;
    }

    // DELETE
    public boolean excluiUsuario(String usuario) {
        Conexao banco = new Conexao();
        resultExclusao = false;
        try {
            banco.abrirConexao();
            PreparedStatement ps = banco.con.prepareStatement(
                    "DELETE FROM usuario WHERE usuario = ?");
            ps.setString(1, usuario);
            ps.executeUpdate();
            resultExclusao = true;
        } catch (SQLException | NullPointerException ec) {
            System.out.println("Erro ao excluir usuário " + ec.getMessage());
        }
        banco.fecharConexao();
        return resultExclusao;
    }
}
