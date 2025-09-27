package com.exemplo.biblioteca.biblioteca.Service;

import com.exemplo.biblioteca.biblioteca.Dao.EmprestimoDAO;
import com.exemplo.biblioteca.biblioteca.Dao.LivroDAO;
import com.exemplo.biblioteca.biblioteca.Model.Emprestimo;
import com.exemplo.biblioteca.biblioteca.Model.Livro;
import com.exemplo.biblioteca.biblioteca.Model.Usuario;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service

public class LivroService {

    private final LivroDAO dao;

    public LivroService(LivroDAO dao){
        this.dao = dao;
    }

    public Livro salvaLivro (Livro livro) throws SQLException {
        return dao.salvaLivro(livro);
    }

    public List<Livro> buscarTodosLivros () throws  SQLException{
        return dao.buscarTodosLivros();
    }

    public Livro buscarPorId (int id) throws  SQLException{
        return dao.buscarLivroPorID(id);
    }

    public Livro atualizarLivro(int id, Livro livro) throws SQLException{
        List<Livro> livros = dao.buscarTodosLivros();

        for(Livro li : livros){
            if(li.getId() == id){
                livro.setId(id);
                dao.atualizarLivro(livro);
                return livro;
            }
        }
        throw new RuntimeException("ID do usuário não existe!");
    }
    public Livro deletarLivro(int id) throws SQLException{
        dao.deletarLivro(id);
        return null;
    }
}
