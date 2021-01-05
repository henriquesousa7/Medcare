package br.edu.ifsp.domain.usecases.acolhimento;

import br.edu.ifsp.domain.entities.Acolhimento;
import br.edu.ifsp.domain.usecases.utils.DAO;

import java.util.Optional;

public interface AcolhimentoDAO extends DAO<Acolhimento, String> {
    @Override
    Optional<Acolhimento> findOne(String key);
}
