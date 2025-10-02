package com.exemplo.biblioteca.biblioteca.Controller;

import com.exemplo.biblioteca.biblioteca.DTO.EmprestimoDTO.CriacaoEmprestimoRequisicaoDTO;
import com.exemplo.biblioteca.biblioteca.DTO.EmprestimoDTO.CriacaoEmprestimoRespostaDTO;
import com.exemplo.biblioteca.biblioteca.Model.Emprestimo;
import com.exemplo.biblioteca.biblioteca.Model.Livro;
import com.exemplo.biblioteca.biblioteca.Service.EmprestimoService;
import com.exemplo.biblioteca.biblioteca.Service.LivroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/emprestimo")

public class EmprestimoController {
    private final EmprestimoService service;

    public EmprestimoController (EmprestimoService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CriacaoEmprestimoRespostaDTO> salvarEmprestimo(
            @RequestBody CriacaoEmprestimoRequisicaoDTO requisicaoEmprestimo
    ){
        try{
         return ResponseEntity.status(HttpStatus.CREATED)
                 .body(service.salvarEmprestimo(requisicaoEmprestimo));
        } catch (SQLException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @GetMapping
    public ResponseEntity< List<CriacaoEmprestimoRespostaDTO> > buscarTodosEmprestimos(){
        try{
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.buscarTodosEmprestimos());
        }catch (SQLException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @GetMapping("/id")
    public ResponseEntity< CriacaoEmprestimoRespostaDTO > buscarPorId(
            @PathVariable int id
    ){
        try{
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.buscarPorId(id));
        } catch (SQLException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @PutMapping("/id")
    public ResponseEntity< Emprestimo > atualizarEmprestimo (
            @PathVariable int id)
    {
        Emprestimo novoEmprestimo = new Emprestimo();

        try{
           service.atualizarEmprestimo(id);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(novoEmprestimo);
    }

    @DeleteMapping("/id")
    public ResponseEntity< Void > deletarEmprestimo (
            @PathVariable int id){
        try{
           service.deletarEmprestimo(id);
           return ResponseEntity.status(HttpStatus.OK)
                   .build();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .build();
    }
}
