package com.exemplo.biblioteca.biblioteca.Controller;


import com.exemplo.biblioteca.biblioteca.DTO.UsuarioDTO.CriacaoUsuarioRequisicaoDTO;
import com.exemplo.biblioteca.biblioteca.DTO.UsuarioDTO.CriacaoUsuarioRespostaDTO;
import com.exemplo.biblioteca.biblioteca.Model.Usuario;
import com.exemplo.biblioteca.biblioteca.Service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CriacaoUsuarioRespostaDTO> salvarUser(
            @RequestBody CriacaoUsuarioRequisicaoDTO requisicaoDTO
            ){
        try{
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(service.salvarUser(requisicaoDTO));
        } catch (SQLException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @GetMapping
    public ResponseEntity< List<CriacaoUsuarioRespostaDTO>> listarUsuarios(){
        try{
           return ResponseEntity.status(HttpStatus.OK)
                   .body(service.buscarTodosUsuarios());
        } catch (SQLException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity< CriacaoUsuarioRespostaDTO > buscarUsuarioPorID (
            @PathVariable int id){
        try{
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.buscarPorId(id));
        }catch (SQLException e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity< CriacaoUsuarioRespostaDTO > atualizarUsuario(
            @PathVariable int id,
            @RequestBody  CriacaoUsuarioRequisicaoDTO requisicaoDTO){

        try{
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.atualizarUsuario(id, requisicaoDTO));

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(
            @PathVariable int id){
        try{
            service.deletarUsuario(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .build();
        }catch (SQLException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }
}
