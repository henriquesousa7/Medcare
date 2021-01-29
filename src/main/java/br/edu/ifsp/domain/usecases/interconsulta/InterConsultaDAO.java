package br.edu.ifsp.domain.usecases.interconsulta;

import br.edu.ifsp.domain.entities.InterConsulta;
import br.edu.ifsp.domain.usecases.utils.DAO;

import java.util.List;
import java.util.Optional;

public interface InterConsultaDAO extends DAO<InterConsulta, Integer> {
    List<InterConsulta> findByDocente(Integer key);
};
