package com.exemplo.biblioteca.biblioteca.DTO.LivroDTO;

public record CriacaoLivroRequisicaoDTO(
        String titulo,
        String autor,
        int ano_publicacao
) {
}
