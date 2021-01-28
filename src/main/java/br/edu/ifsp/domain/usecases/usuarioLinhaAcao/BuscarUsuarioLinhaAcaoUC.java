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

    public List<UsuarioLinhaAcao> findByAcolhimento(Integer pront_acolhimento){
        if (pront_acolhimento == null)
            throw new IllegalArgumentException("Prontuario can not be null.");
        return usuarioLinhaAcaoDAO.findByAcolhimento(pront_acolhimento);
    }

    public List<UsuarioLinhaAcao> findByDocente(Integer pront_docente) {
        if (pront_docente == null)
            throw new IllegalArgumentException("Prontuario can not be null.");
        return usuarioLinhaAcaoDAO.findByDocente(pront_docente);
    }
}
