package br.edu.ifsp.domain.usecases.usuarioLinhaAcao;

import br.edu.ifsp.domain.entities.UsuarioLinhaAcao;
import br.edu.ifsp.domain.usecases.utils.DAO;

import java.util.List;
import java.util.Optional;

public interface UsuarioLinhaAcaoDAO extends DAO<UsuarioLinhaAcao, Integer> {
    List<UsuarioLinhaAcao> findByAcolhimento(Integer Key);
    List<UsuarioLinhaAcao> findByDocente(Integer Key);
}
