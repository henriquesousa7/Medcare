package br.edu.ifsp.domain.usecases.docente;

import br.edu.ifsp.domain.entities.Docente;
import br.edu.ifsp.domain.usecases.utils.DAO;

import java.util.Optional;

public interface DocenteDAO extends DAO<Docente, Integer> {
    @Override
    Optional<Docente> findOne(Integer key);
}
