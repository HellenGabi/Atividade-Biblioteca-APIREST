package com.exemplo.biblioteca.biblioteca.Service;

import com.exemplo.biblioteca.biblioteca.DTO.EmprestimoDTO.CriacaoEmprestimoRequisicaoDTO;
import com.exemplo.biblioteca.biblioteca.DTO.EmprestimoDTO.CriacaoEmprestimoRespostaDTO;
import com.exemplo.biblioteca.biblioteca.Dao.EmprestimoDAO;
import com.exemplo.biblioteca.biblioteca.Mapper.EmprestimoMapper.EmprestimoMapper;
import com.exemplo.biblioteca.biblioteca.Model.Emprestimo;
import com.exemplo.biblioteca.biblioteca.Model.Usuario;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmprestimoService {
    private final EmprestimoMapper mapper;
    private final EmprestimoDAO dao;

    public EmprestimoService(EmprestimoDAO dao, EmprestimoMapper mapper){
        this.mapper = mapper;
        this.dao = dao;
    }

    public CriacaoEmprestimoRespostaDTO salvarEmprestimo (CriacaoEmprestimoRequisicaoDTO requisicaoDTO) throws SQLException{
        return mapper.paraRespostas(dao.salvarEmprestimo(requisicaoDTO));
    }

    public List<CriacaoEmprestimoRespostaDTO> buscarTodosEmprestimos () throws  SQLException{
        List<Emprestimo> emprestimos = dao.buscarTodosEmprestimos();
        List<CriacaoEmprestimoRespostaDTO> respostaDTOS = new ArrayList<>();

        for(Emprestimo emprestimo : emprestimos){
            respostaDTOS.add(mapper.paraRespostas(emprestimo));
        }
        return respostaDTOS;
    }

    public CriacaoEmprestimoRespostaDTO buscarPorId (int id) throws  SQLException{
       Emprestimo emprestimo = dao.buscarPorId(id);

       if(emprestimo == null){
           throw new RuntimeException("O ID nao existe");
       }
        return mapper.paraRespostas(emprestimo);
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
            if(!dao.emprestimoExiste(id)){
                throw new RuntimeException("O ID do livre nao existe");
            }
            dao.deletarEmprestimo(id);
            }
}

