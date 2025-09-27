package com.exemplo.biblioteca.biblioteca.Service;

import com.exemplo.biblioteca.biblioteca.Dao.EmprestimoDAO;
import com.exemplo.biblioteca.biblioteca.Model.Emprestimo;
import com.exemplo.biblioteca.biblioteca.Model.Usuario;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class EmprestimoService {

    private final EmprestimoDAO dao;

    public EmprestimoService(EmprestimoDAO dao){
        this.dao = dao;
    }

    public Emprestimo salvarEmprestimo (Emprestimo emprestimo) throws SQLException{
        return dao.salvarEmprestimo(emprestimo);
    }

    public List<Emprestimo> buscarTodosEmprestimos () throws  SQLException{
        return dao.buscarTodosEmprestimos();
    }

    public List<Emprestimo> buscarPorId (int id) throws  SQLException{
        return (List<Emprestimo>) dao.buscarPorId(id);
    }

    public void atualizarEmprestimo (int id) throws SQLException{
        List<Emprestimo> emprestimos = dao.buscarTodosEmprestimos();
        for(Emprestimo emprestimo: emprestimos){
            if(emprestimo.getId()==id){
                dao.atualizarEmprestimo(emprestimo);
                return;
            }
        }
        throw new RuntimeException( "ID nao encontrado para fazer a atualização");
    }

    public void deletarEmprestimo (int id) throws  SQLException {
        List<Emprestimo> emprestimos = dao.buscarTodosEmprestimos();

        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getId() == id){
                dao.deletarEmprestimo(id);
                return;
            }
        }
        throw new RuntimeException("ID nao encontrado para fazer o delete deste usuario.");
    }
}
