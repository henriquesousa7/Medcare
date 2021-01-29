package br.edu.ifsp.domain.usecases.Mantenedor;

import br.edu.ifsp.domain.entities.Docente;
import br.edu.ifsp.domain.entities.Mantenedor;
import br.edu.ifsp.domain.usecases.utils.DAO;

public interface MantenedorDAO extends DAO<Mantenedor, Integer> {
    Mantenedor checkLogin(String email, Integer prontuario);
}
