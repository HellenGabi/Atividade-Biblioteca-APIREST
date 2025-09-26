package com.exemplo.biblioteca.biblioteca.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String URL = "jdbc:mysql://localhost:3306/biblioteca?useSSL=false&serverTimezone=UTC";
    private static final String SENHA = "root";
    private static final String USUARIO = "mysqlPW";

    public static Connection conectar() throws SQLException{
        return DriverManager.getConnection(URL, SENHA, USUARIO);
    }
}
