package com.exemplo.biblioteca.biblioteca.Controller;

import com.exemplo.biblioteca.biblioteca.DTO.LivroDTO.CriacaoLivroRequisicaoDTO;
import com.exemplo.biblioteca.biblioteca.DTO.LivroDTO.CriacaoLivroRespostaDTO;
import com.exemplo.biblioteca.biblioteca.Model.Livro;
import com.exemplo.biblioteca.biblioteca.Service.LivroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/livro")

public class LivroController {

    private final LivroService service;

    public LivroController (LivroService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CriacaoLivroRespostaDTO> salvaLivro(
            @RequestBody CriacaoLivroRequisicaoDTO requisicaoUsuario
    ){
        try{
           return ResponseEntity.status(HttpStatus.CREATED)
                    .body(service.salvaLivro(requisicaoUsuario));
        } catch (SQLException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @GetMapping
    public ResponseEntity< List<CriacaoLivroRespostaDTO>> buscarTodosLivros(){
        try{
            return  ResponseEntity.status(HttpStatus.OK)
                    .body(service.buscarTodosLivros());
        }catch (SQLException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity < CriacaoLivroRespostaDTO > buscarPorId(
            @PathVariable int id
    ) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.buscarPorId(id));
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity < Livro > atualizarLivro(
            @PathVariable int id, @RequestBody Livro livro){
        Livro newLivro = new Livro();
        try{
            newLivro = service.atualizarLivro(id, livro);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(newLivro);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLivro(
            @PathVariable int id){
        try{
            service.deletarLivro(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .build();
        }catch (SQLException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }
}
