package br.edu.ifsp.domain.usecases.discente;

import br.edu.ifsp.domain.entities.Discente;
import br.edu.ifsp.domain.usecases.utils.DAO;

import java.util.Optional;

public interface DiscenteDAO extends DAO<Discente, Integer> {
    @Override
    Optional<Discente> findOne(Integer key);
}
