package com.exemplo.biblioteca.biblioteca.Controller;


import com.exemplo.biblioteca.biblioteca.Model.Usuario;
import com.exemplo.biblioteca.biblioteca.Service.UsuarioService;
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
    public Usuario salvarUser(
            @RequestBody Usuario usuario
            ){
        Usuario newUsuario = new Usuario();

        try{
            newUsuario = service.salvarUser(usuario);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return newUsuario;
    }

    @GetMapping
    public List<Usuario> listarUsuarios(){
        List<Usuario> listarUsers = new ArrayList<>();

        try{
            listarUsers = service.buscarTodosUsuarios();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return listarUsers;
    }

    @GetMapping("/{id}")
    public Usuario buscarUsuarioPorID(@PathVariable int id){
        Usuario newUsuario = new Usuario();
        try{
            newUsuario = service.buscarPorId(id);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return newUsuario;
    }

    @PutMapping("/{id}")
    public Usuario atualizarUsuario(@PathVariable int id, @RequestBody Usuario usuario){
        Usuario newUsuario = new Usuario();
        try{
            newUsuario = service.atualizarUsuario(id, usuario);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return newUsuario;
    }
    @DeleteMapping("/{id}")
    public Usuario deletarUsuario(@PathVariable int id){
        Usuario newUsuario = new Usuario();
        try{
            newUsuario = service.deletarUsuario(id);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return newUsuario;
    }
}
