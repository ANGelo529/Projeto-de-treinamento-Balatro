package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String URL = "jdbc:mysql://localhost:3306/tcb_BD?useSSL=false&serverTimezone=UTC";
    private static final String USER = "aluno";
    private static final String SENHA = "aluno";

    public static Connection conectarBD() throws SQLException {
        return DriverManager.getConnection(URL, USER, SENHA);
    }
}
