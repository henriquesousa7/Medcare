package br.edu.ifsp.domain.usecases.atendimento;

import br.edu.ifsp.domain.entities.Atendimento;
import br.edu.ifsp.domain.usecases.utils.DAO;

import java.util.Optional;

public interface AtendimentoDAO extends DAO<Atendimento, Integer> {
    Optional<Atendimento> findByDiscente(Integer key);
}
