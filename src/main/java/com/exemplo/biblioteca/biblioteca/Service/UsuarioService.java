package com.exemplo.biblioteca.biblioteca.Service;

import com.exemplo.biblioteca.biblioteca.DTO.UsuarioDTO.CriacaoUsuarioRequisicaoDTO;
import com.exemplo.biblioteca.biblioteca.DTO.UsuarioDTO.CriacaoUsuarioRespostaDTO;
import com.exemplo.biblioteca.biblioteca.Dao.UsuarioDAO;
import com.exemplo.biblioteca.biblioteca.Mapper.UsuarioMapper.UsuarioMapper;
import com.exemplo.biblioteca.biblioteca.Model.Usuario;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service

public class UsuarioService {
    private final UsuarioMapper mapper;
    private final UsuarioDAO dao;

    public UsuarioService(UsuarioDAO dao, UsuarioMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    public CriacaoUsuarioRespostaDTO salvarUser(CriacaoUsuarioRequisicaoDTO requisicaoDTO) throws SQLException {
        return mapper.paraRespostaDTO(dao.salvarUser(mapper.paraEntidade(requisicaoDTO)));
    }

    public List<CriacaoUsuarioRespostaDTO> buscarTodosUsuarios() throws SQLException {
        List<Usuario> usuarios = dao.buscarTodosUsuarios();
        List<CriacaoUsuarioRespostaDTO> respostaDTOS = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            respostaDTOS.add(mapper.paraRespostaDTO(usuario));
        }
        return respostaDTOS;
    }

    public CriacaoUsuarioRespostaDTO buscarPorId(int id) throws SQLException {
        Usuario usuario = dao.buscarPorId(id);
        if (usuario == null) {
            throw new RuntimeException("ID do usuario não existe!!");
        }
        return mapper.paraRespostaDTO(usuario);
    }

    public CriacaoUsuarioRespostaDTO atualizarUsuario(int id, CriacaoUsuarioRequisicaoDTO requisicaoDTO) throws SQLException {
        Usuario usuario = dao.buscarPorId(id);

        if (usuario == null) {
            throw new RuntimeException("O usuario nao existe");
        }
        Usuario newUser = mapper.paraUpdate(requisicaoDTO, usuario);
        dao.atualizarUsuario(newUser);
        return mapper.paraRespostaDTO(newUser);

    }

    public void deletarUsuario(int id) throws SQLException {
        if (!dao.usersExiste(id)) {
            throw new RuntimeException("O ID nao existe!!");
        }
        dao.deletarUsuario(id);
    }
}




