package com.exemplo.biblioteca.biblioteca.Service;

import com.exemplo.biblioteca.biblioteca.Dao.EmprestimoDAO;
import com.exemplo.biblioteca.biblioteca.Dao.UsuarioDAO;
import com.exemplo.biblioteca.biblioteca.Model.Usuario;

import java.sql.SQLException;
import java.util.List;

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


    public List<Usuario> buscarPorId (Usuario usuario) throws  SQLException{
        return (List<Usuario>) dao.buscarPorId(usuario);
    }

    public void atualizarUsuario (int id) throws SQLException{
    List<Usuario> usuarios =dao.buscarTodosUsuarios();
        for(Usuario usuario: usuarios){
            if(usuario.getId()==id){
                dao.atualizarUsuario(usuario);
                return;
            }
        }
        throw new RuntimeException( "ID nao encontrado para fazer a atualização");
    }

    public void deletarUsuario (int id) throws  SQLException {
        List<Usuario> usuarios = dao.buscarTodosUsuarios();

        for (Usuario usuario : usuarios) {

            if (usuario.getId() == id){
                dao.deletarUsuario(id);
                return;
            }
        }
        throw new RuntimeException("ID nao encontrado para fazer o delete deste usuario.");
    }



    }




