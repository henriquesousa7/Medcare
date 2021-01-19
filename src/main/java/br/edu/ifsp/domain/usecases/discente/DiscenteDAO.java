package br.edu.ifsp.domain.usecases.discente;

import br.edu.ifsp.domain.entities.Acolhimento;
import br.edu.ifsp.domain.entities.Discente;
import br.edu.ifsp.domain.usecases.utils.DAO;

import java.util.Optional;

public interface DiscenteDAO extends DAO<Discente, Integer> {
    Discente checkLogin(String email, Integer prontuario);
}
