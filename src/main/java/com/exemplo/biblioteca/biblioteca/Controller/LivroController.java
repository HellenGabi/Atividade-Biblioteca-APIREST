package com.exemplo.biblioteca.biblioteca.Controller;

import com.exemplo.biblioteca.biblioteca.Model.Livro;
import com.exemplo.biblioteca.biblioteca.Model.Usuario;
import com.exemplo.biblioteca.biblioteca.Service.LivroService;
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
    public Livro salvaLivro(
            @RequestBody Livro livro
    ){
        Livro newLivro = new Livro();

        try{
            newLivro = service.salvaLivro(livro);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return newLivro;
    }

    @GetMapping
    public List<Livro> buscarTodosLivros(){
        List<Livro> listarLivros = new ArrayList<>();

        try{
            listarLivros = service.buscarTodosLivros();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return listarLivros;
    }

    @GetMapping("/{id}")
    public Livro buscarPorId(
            @PathVariable int id
    ){
        Livro novoLivro = new Livro();

        try{
            novoLivro = (Livro) service.buscarPorId(id);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return novoLivro;
    }

    @PutMapping("/{id}")
    public Livro atualizarLivro(@PathVariable int id, @RequestBody Livro livro){
        Livro newLivro = new Livro();
        try{
            newLivro = service.atualizarLivro(id, livro);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return newLivro;
    }

    @DeleteMapping("/{id}")
    public Livro deletarLivro(@PathVariable int id){
        Livro newLivro = new Livro();
        try{
            newLivro = service.deletarLivro(id);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return newLivro;
    }
}
