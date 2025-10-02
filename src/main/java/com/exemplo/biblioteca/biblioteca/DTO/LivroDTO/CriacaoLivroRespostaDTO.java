package com.exemplo.biblioteca.biblioteca.DTO.LivroDTO;

public record CriacaoLivroRespostaDTO(
        int id,
        String titulo,
        String autor,
        int ano_publicacao
) {
}
