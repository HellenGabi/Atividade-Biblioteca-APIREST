package com.exemplo.biblioteca.biblioteca.Service;

import com.exemplo.biblioteca.biblioteca.Dao.EmprestimoDAO;
import com.exemplo.biblioteca.biblioteca.Dao.UsuarioDAO;
import com.exemplo.biblioteca.biblioteca.Model.Usuario;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service

public class UsuarioService {


    private final UsuarioDAO dao;

    public UsuarioService(UsuarioDAO dao){
        this.dao = dao;
    }

    public Usuario salvarUser (Usuario usuario) throws SQLException{
        return  dao.salvarUser(usuario);
    }

    public List<Usuario> buscarTodosUsuarios () throws  SQLException{
        return dao.buscarTodosUsuarios();
    }


    public Usuario buscarPorId (int id) throws  SQLException{
        return dao.buscarPorId(id);
    }

    public Usuario atualizarUsuario(int id, Usuario usuario) throws SQLException{
        List<Usuario> usuarios = dao.buscarTodosUsuarios();

        for(Usuario u : usuarios){
            if(u.getId() == id){
                usuario.setId(id);
                dao.atualizarUsuario(usuario);
                return usuario;
            }
        }
        throw new RuntimeException("ID do usuário não existe!");
    }

    public Usuario deletarUsuario(int id) throws SQLException{
        dao.deletarUsuario(id);
        return null;
    }



    }




