package br.edu.ifsp.domain.usecases.discente;

import br.edu.ifsp.domain.entities.Discente;
import br.edu.ifsp.domain.entities.Docente;

import java.util.List;
import java.util.Optional;

public class BuscarDiscenteUC {
    private DiscenteDAO discenteDAO;

    public BuscarDiscenteUC(DiscenteDAO discenteDAO) {
        this.discenteDAO = discenteDAO;
    }

    public Optional<Discente> findOne(Integer prontuario){
        if (prontuario == null)
            throw new IllegalArgumentException("Prontuario can not be null.");
        return discenteDAO.findOne(prontuario);
    }

    public List<Discente> findAll(){
        return discenteDAO.findAll();
    }
}
