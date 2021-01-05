package br.edu.ifsp.domain.usecases.linhaCuidado;

import br.edu.ifsp.domain.entities.LinhaCuidado;
import br.edu.ifsp.domain.usecases.utils.DAO;

import java.util.Optional;

public interface LinhaCuidadoDAO extends DAO<LinhaCuidado, Integer> {
    @Override
    Optional<LinhaCuidado> findOne(Integer key);
}
