package br.edu.ifsp.domain.usecases.atendimento;

import br.edu.ifsp.domain.entities.Atendimento;
import br.edu.ifsp.domain.usecases.utils.DAO;

import java.util.List;
import java.util.Optional;

public interface AtendimentoDAO extends DAO<Atendimento, Integer> {
    List<Atendimento> findByDocenteAcao(Integer key);
    List<Atendimento> findByDiscente(Integer key);
}
