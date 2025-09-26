package com.exemplo.biblioteca.biblioteca.Dao;

import com.exemplo.biblioteca.biblioteca.Database.Conexao;
import com.exemplo.biblioteca.biblioteca.Model.Livro;
import com.exemplo.biblioteca.biblioteca.Model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {

    public void salvaLivro (Livro livro) throws SQLException {

        String query = "INSERT INTO livro(titulo, autor,ano_publicacao)VALUES(?,?,?)";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setInt(3, livro.getAno_publicacao());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();

            if(rs.next()) {
                livro.setId(rs.getInt(1));
            }
        }
    }

    public List<Livro> buscarTodos () throws SQLException{

        String query = "SELECT id, titulo, autor, ano_publicacao FROM livro";

        List<Livro> livros = new ArrayList<>();

        try (Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(query)){
        ResultSet rs = stmt.executeQuery();

        while(rs.next()){
            int id = rs.getInt("id");
            String titulo = rs.getString("titulo");
            String autor = rs.getString("autor");
            int ano_publicacao = rs.getInt("ano_publicacao");
            var livro = new Livro(id, titulo, autor, ano_publicacao);
            livros.add(livro);
        }
            }
        return livros;
    }
    public Livro buscarPorId(int id) throws SQLException{
        String query = "SELECT id, titulo, autor, ano_publicacao FROM livro WHERE id = ?";

        int newId = 0;
        String titulo = "";
        String autor = "";
        int ano_publicacao = 0;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                newId = rs.getInt("id");
                titulo = rs.getString("nome");
                autor = rs.getString("email");
                ano_publicacao = rs.getInt("ano_publicacao");
            }
        }

        return new Livro(newId, titulo, autor, ano_publicacao);
    }

    public void atualizar(Livro livro) throws SQLException{
        String query = "UPDATE livro SET titulo = ?, autor = ? WHERE id = ? ";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setInt(3, livro.getId());
            stmt.executeUpdate();
        }
    }

    public void deletar(int id) throws  SQLException{

     String query = "DELECT FROM livro WHERE id = ?";

     try(Connection conn = Conexao.conectar();
     PreparedStatement stmt = conn.prepareStatement(query)){
     stmt.setInt(1, id);
     stmt.executeUpdate();
                    }
        }


    }



