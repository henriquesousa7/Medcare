package br.edu.ifsp.domain.usecases.usuarioLinhaAcao;

import br.edu.ifsp.domain.entities.Usuario;
import br.edu.ifsp.domain.entities.UsuarioLinhaAcao;

import java.util.List;
import java.util.Optional;

public class BuscarUsuarioLinhaAcaoUC {
    private UsuarioLinhaAcaoDAO usuarioLinhaAcaoDAO;

    public BuscarUsuarioLinhaAcaoUC(UsuarioLinhaAcaoDAO usuarioLinhaAcaoDAO) {
        this.usuarioLinhaAcaoDAO = usuarioLinhaAcaoDAO;
    }

    public Optional<UsuarioLinhaAcao> findOne(Integer id){
        if (id == null)
            throw new IllegalArgumentException("Id can not be null.");
        return usuarioLinhaAcaoDAO.findOne(id);
    }

    public List<UsuarioLinhaAcao> findAll(){
        return usuarioLinhaAcaoDAO.findAll();
    }
}
