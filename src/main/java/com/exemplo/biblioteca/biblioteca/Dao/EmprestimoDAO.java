package com.exemplo.biblioteca.biblioteca.Dao;

import com.exemplo.biblioteca.biblioteca.Database.Conexao;
import com.exemplo.biblioteca.biblioteca.Model.Emprestimo;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmprestimoDAO {


    public Emprestimo salvarEmprestimo (Emprestimo emprestimo) throws SQLException {

        String query = "INSERT INTO emprestimo ( livro_id, usuario_id, data_emprestimo, data_devolucao)VALUES(?,?,?,?)";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            stmt.setInt(1, emprestimo.getLivro_id());
            stmt.setInt(2, emprestimo.getUsuario_id());
            stmt.setDate(3, Date.valueOf(emprestimo.getData_empretimo()));
            stmt.setDate(4, Date.valueOf(emprestimo.getData_devolucao()));
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();

            if(rs.next()) {
                emprestimo.setId(rs.getInt(1));
            }
        }
        return emprestimo;
    }

    public List<Emprestimo> buscarTodosEmprestimos () throws SQLException{

        String query = "SELECT id,livro_id, usuario_id, data_emprestimo, data_devolucao FROM emprestimo";

        List<Emprestimo> emprestimos = new ArrayList<>();

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)){
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                int livro_id = rs.getInt("livro_id");
                int usuario_id = rs.getInt("usuario_id");
                LocalDate data_emprestimo = rs.getDate("data_emprestimo").toLocalDate();
                LocalDate data_devolucao = rs.getDate("data_devolucao").toLocalDate();

                var emprestimo = new Emprestimo(id, livro_id, usuario_id, data_emprestimo, data_devolucao );
                emprestimos.add(emprestimo);
            }
        }
        return emprestimos;
    }

    public Emprestimo buscarPorId(int id) throws SQLException{
        Emprestimo emprestimo = new Emprestimo();
        String query = """
                SELECT   id
                        ,livro_id
                        , usuario_id
                        , data_emprestimo
                        , data_devolucao
                FROM emprestimo WHERE id = ?
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                emprestimo.setId(rs.getInt("id"));
                emprestimo.setLivro_id(rs.getInt("livro_id"));
                emprestimo.setUsuario_id(rs.getInt("usuario_id"));
                emprestimo.setData_empretimo(rs.getDate("data_emprestimo").toLocalDate());
                emprestimo.setData_devolucao(rs.getDate("data_devolucao").toLocalDate());
            }
        }
        return emprestimo;
    }

    public void atualizarEmprestimo(Emprestimo emprestimo) throws SQLException{
        String query = "UPDATE emprestimo SET livro_id = ?, usuario_id = ?, data_emprestimo = ?, data_devolucao = ?, WHERE id = ? ";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, emprestimo.getLivro_id());
            stmt.setInt(2, emprestimo.getUsuario_id());
            stmt.setDate(4, Date.valueOf(emprestimo.getData_empretimo()));
            stmt.setDate(5, Date.valueOf(emprestimo.getData_devolucao()));
            stmt.executeUpdate();
        }
    }

    public void deletarEmprestimo (int id) throws  SQLException{

        String query = "DELECT FROM emprestimo WHERE id = ?";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

}
