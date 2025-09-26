package com.exemplo.biblioteca.biblioteca.Model;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.Date;

public class Emprestimo {

    private int id;
    private int livro_id;
    private int usuario_id;

    private LocalDate data_empretimo;
    private LocalDate data_devolucao;

    public Emprestimo(){

    }

    public Emprestimo(int id, int livro_id, int usuario_id, LocalDate data_empretimo, LocalDate data_devolucao) {
        this.id = id;
        this.livro_id = livro_id;
        this.usuario_id = usuario_id;
        this.data_empretimo = data_empretimo;
        this.data_devolucao = data_devolucao;
    }

    public Emprestimo(int livro_id, int usuario_id, LocalDate data_empretimo, LocalDate data_devolucao) {
        this.livro_id = livro_id;
        this.usuario_id = usuario_id;
        this.data_empretimo = data_empretimo;
        this.data_devolucao = data_devolucao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLivro_id() {
        return livro_id;
    }

    public void setLivro_id(int livro_id) {
        this.livro_id = livro_id;
    }

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public LocalDate getData_empretimo() {
        return data_empretimo;
    }

    public void setData_empretimo(LocalDate data_empretimo) {
        this.data_empretimo = data_empretimo;
    }

    public LocalDate getData_devolucao() {
        return data_devolucao;
    }

    public void setData_devolucao(LocalDate data_devolucao) {
        this.data_devolucao = data_devolucao;
    }
}
