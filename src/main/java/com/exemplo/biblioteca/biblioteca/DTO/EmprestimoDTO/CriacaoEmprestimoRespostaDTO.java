package com.exemplo.biblioteca.biblioteca.DTO.EmprestimoDTO;

import java.time.LocalDate;

public record CriacaoEmprestimoRespostaDTO(
        int id,
        int livro_id,
        int usuario_id,
        LocalDate data_empretimo,
        LocalDate data_devolucao
) {
}
