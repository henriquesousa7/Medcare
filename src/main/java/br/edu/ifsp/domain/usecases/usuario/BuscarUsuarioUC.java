package br.edu.ifsp.domain.usecases.usuario;

import br.edu.ifsp.domain.entities.Usuario;

import java.util.List;
import java.util.Optional;

public class BuscarUsuarioUC {
    private UsuarioDAO usuarioDAO;

    public BuscarUsuarioUC(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public Optional<Usuario> findOne(String cpf){
        if (cpf == null)
            throw new IllegalArgumentException("CPF can not be null.");
        return usuarioDAO.findOne(cpf);
    }

    public List<Usuario> findAll(){
        return usuarioDAO.findAll();
    }
}
