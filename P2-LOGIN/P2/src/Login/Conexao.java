package Login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexao {
    public Connection con = null;
    public Statement stmt = null;
    public ResultSet resultset = null;

    // CONFIGURAÇÕES DO BANCO DE DADOS
    // Usuário e senha vêm de variáveis de ambiente (DB_USER / DB_PASSWORD),
    // assim a senha do banco não fica no código nem vai para o GitHub.
    private final String servidor = "jdbc:mysql://localhost:3306/bd_Login";
    private final String usuario = System.getenv().getOrDefault("DB_USER", "root");
    private final String senha = System.getenv().getOrDefault("DB_PASSWORD", "");
    private final String driver = "com.mysql.cj.jdbc.Driver";

    public Connection abrirConexao() {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(servidor, usuario, senha);
            stmt = con.createStatement();
            System.out.println("Conexão aberta com sucesso");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erro ao acessar banco de dados, verifique! " + e.getMessage());
        }
        return con;
    }

    public void fecharConexao() {
        try {
            if (con != null) {
                con.close();
                System.out.println("Conexão finalizada com sucesso");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao encerrar conexão: " + e.getMessage());
        }
    }
}
