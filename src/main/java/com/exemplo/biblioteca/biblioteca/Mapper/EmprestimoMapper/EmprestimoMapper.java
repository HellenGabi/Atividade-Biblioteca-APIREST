package com.exemplo.biblioteca.biblioteca.Mapper.EmprestimoMapper;

import com.exemplo.biblioteca.biblioteca.DTO.EmprestimoDTO.CriacaoEmprestimoRequisicaoDTO;
import com.exemplo.biblioteca.biblioteca.DTO.EmprestimoDTO.CriacaoEmprestimoRespostaDTO;
import com.exemplo.biblioteca.biblioteca.Model.Emprestimo;

public class EmprestimoMapper {

    public Emprestimo paraEntidade(CriacaoEmprestimoRequisicaoDTO requisicaoDTO){
        return new Emprestimo(
                 requisicaoDTO.livro_id()
                ,requisicaoDTO.usuario_id()
                ,requisicaoDTO.data_empretimo()
                ,requisicaoDTO.data_devolucao());
    }
    public CriacaoEmprestimoRespostaDTO paraRespostas(Emprestimo emprestimo){
        return new CriacaoEmprestimoRespostaDTO(
                         emprestimo.getId()
                        ,emprestimo.getLivro_id()
                        ,emprestimo.getUsuario_id()
                        ,emprestimo.getData_empretimo()
                        ,emprestimo.getData_devolucao());
    }
}
