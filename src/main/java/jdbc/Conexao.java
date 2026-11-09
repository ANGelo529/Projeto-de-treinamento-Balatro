package main.java.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private final String URL = "jdbc:mysql://localhost:3306/TCB_Balatro";
    private final String USER = "aluno";
    private final String SENHA = "aluno";

    private Conexao(){}

    public Conexao getInstance(){
        return new Conexao();
    }

    public Connection conectarBD() throws SQLException{
        return DriverManager.getConnection(URL,USER,SENHA);
    }
}
