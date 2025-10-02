package com.exemplo.biblioteca.biblioteca.Mapper.LivroMapper;


import com.exemplo.biblioteca.biblioteca.DTO.LivroDTO.CriacaoLivroRequisicaoDTO;
import com.exemplo.biblioteca.biblioteca.DTO.LivroDTO.CriacaoLivroRespostaDTO;
import com.exemplo.biblioteca.biblioteca.Model.Livro;
import org.springframework.stereotype.Component;

@Component
public class LivroMapper {

    public Livro paraEntidade(CriacaoLivroRequisicaoDTO requisicaoDTO){
        return new Livro(requisicaoDTO.titulo(),
                requisicaoDTO.autor(),
                requisicaoDTO.ano_publicacao());
    }
    public CriacaoLivroRespostaDTO paraRespostaDto(Livro livro ){
        return new CriacaoLivroRespostaDTO(livro.getId(),
                livro.getTitulo(),
                livro.getAutor(),
                livro.getAno_publicacao());
    }
}
