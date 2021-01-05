package br.edu.ifsp.domain.usecases.linhaAcao;

import br.edu.ifsp.domain.entities.LinhaAcao;
import br.edu.ifsp.domain.usecases.utils.DAO;

import java.util.Optional;

public interface LinhaAcaoDAO extends DAO<LinhaAcao, Integer> {
    @Override
    Optional<LinhaAcao> findOne(Integer key);
}
