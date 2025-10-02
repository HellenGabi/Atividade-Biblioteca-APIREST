package com.exemplo.biblioteca.biblioteca.Service;

import com.exemplo.biblioteca.biblioteca.DTO.LivroDTO.CriacaoLivroRequisicaoDTO;
import com.exemplo.biblioteca.biblioteca.DTO.LivroDTO.CriacaoLivroRespostaDTO;
import com.exemplo.biblioteca.biblioteca.Dao.EmprestimoDAO;
import com.exemplo.biblioteca.biblioteca.Dao.LivroDAO;
import com.exemplo.biblioteca.biblioteca.Mapper.LivroMapper.LivroMapper;
import com.exemplo.biblioteca.biblioteca.Model.Emprestimo;
import com.exemplo.biblioteca.biblioteca.Model.Livro;
import com.exemplo.biblioteca.biblioteca.Model.Usuario;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service

public class LivroService {
    private final LivroMapper mapper;
    private final LivroDAO dao;

    public LivroService(LivroDAO dao, LivroMapper mapper){
        this.dao = dao;
        this.mapper = mapper;
    }

    //trem pra adiocionar ja com as paradas que o prof passou e eu preciso aprender.
    public CriacaoLivroRespostaDTO salvaLivro (CriacaoLivroRequisicaoDTO requisicaoDTO) throws SQLException {
        return mapper.paraRespostaDto(dao.salvaLivro(mapper.paraEntidade(requisicaoDTO)));
    }

    //listando já da forma certa e eu tb preciso aprender essa coisa, nao entendi.
    public List<CriacaoLivroRespostaDTO> buscarTodosLivros () throws  SQLException{
        List<Livro> livros = dao.buscarTodosLivros();
        List<CriacaoLivroRespostaDTO> respostaDTOS = new ArrayList<>();
        for(Livro livro : livros){
            respostaDTOS.add(mapper.paraRespostaDto(livro));
        }
        return respostaDTOS;
    }

    public CriacaoLivroRespostaDTO buscarPorId (int id) throws  SQLException{
        Livro livro = dao.buscarLivroPorID(id);

        if(livro == null){
            throw new RuntimeException("O ID nao existe");
        }
        return mapper.paraRespostaDto(livro);

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
        throw new RuntimeException("ID do livro não existe!");
    }
    public void deletarLivro(int id) throws SQLException {
        if (!dao.livroExiste(id)) {
            throw new RuntimeException("O ID do livre nao existe");
        }
            dao.deletarLivro(id);
    }
}
