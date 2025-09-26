package com.exemplo.biblioteca.biblioteca.Service;

import com.exemplo.biblioteca.biblioteca.Dao.EmprestimoDAO;
import com.exemplo.biblioteca.biblioteca.Model.Emprestimo;

import java.sql.SQLException;

public class EmprestimoService {

    private final EmprestimoDAO dao;

    public EmprestimoService(EmprestimoDAO dao){
        this.dao = dao;
    }

    public Emprestimo salvarEmprestimo (Emprestimo emprestimo) throws SQLException{
        return dao.salvarEmprestimo(emprestimo);
    }

    //public Emprestimo;
}
