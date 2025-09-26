package com.exemplo.biblioteca.biblioteca.Dao;

import com.exemplo.biblioteca.biblioteca.Database.Conexao;
import com.exemplo.biblioteca.biblioteca.Model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public Usuario salvarUser(Usuario usuario) throws SQLException {

        String query = "INSERT usuario(nome, email)VALUES(?,?)";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();

            if(rs.next()){
                usuario.setId(usuario.getId());
            }
        }
        return usuario;
    }

    public List<Usuario> buscarTodosUsuarios() throws SQLException {

        String query = " SELECT id, nome, email FROM usuarios";

        List<Usuario> usuarios = new ArrayList<>();

        try(Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(query)){
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                var usuario = new Usuario(id, nome, email);
                usuarios.add(usuario);
            }
        }
        return usuarios;
    }

    public Usuario buscarPorId(Usuario usuario) throws SQLException{
        String query = "SELECT id, nome, email FROM usuario WHERE id = ?";

        int newId = 0;
        String nome = "";
        String email = "";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, usuario.getId());
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                newId = rs.getInt("id");
                nome = rs.getString("nome");
                email = rs.getString("email");
            }
        }
        return new Usuario(newId, nome, email);
    }

    public void atualizarUsuario(Usuario usuario) throws SQLException{
        String query = "UPDATE usuario SET nome = ?, email = ? WHERE id = ? ";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setInt(3, usuario.getId());
            stmt.executeUpdate();
        }
    }

    public void deletarUsuario(int id) throws  SQLException{

        String query = "DELECT FROM usuario WHERE id = ?";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }


}
