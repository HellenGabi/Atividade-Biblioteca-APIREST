package com.exemplo.biblioteca.biblioteca.Controller;

import com.exemplo.biblioteca.biblioteca.Model.Emprestimo;
import com.exemplo.biblioteca.biblioteca.Model.Livro;
import com.exemplo.biblioteca.biblioteca.Service.EmprestimoService;
import com.exemplo.biblioteca.biblioteca.Service.LivroService;
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
    public Emprestimo salvarEmprestimo(
            @RequestBody Emprestimo emprestimo
    ){
        Emprestimo newEmprestimo = new Emprestimo();

        try{
            newEmprestimo = service.salvarEmprestimo(emprestimo);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return newEmprestimo;
    }

    @GetMapping
    public List<Emprestimo> buscarTodosEmprestimos(){
        List<Emprestimo> listarEmprestimos = new ArrayList<>();

        try{
            listarEmprestimos = service.buscarTodosEmprestimos();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return listarEmprestimos;
    }

    @GetMapping("/id")
    public Emprestimo buscarPorId(
            @PathVariable int id
    ){
        Emprestimo novoEmprestimo = new Emprestimo();

        try{
            novoEmprestimo = (Emprestimo) service.buscarPorId(id);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return novoEmprestimo;
    }

    @PutMapping("/id")
    public Emprestimo atualizarEmprestimo (
            @PathVariable int id)
    {
        Emprestimo novoEmprestimo = new Emprestimo();

        try{
           service.atualizarEmprestimo(id);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return novoEmprestimo;
    }

    @DeleteMapping("/id")
    public void deletarEmprestimo (@PathVariable int id){
        try{
           service.deletarEmprestimo(id);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
