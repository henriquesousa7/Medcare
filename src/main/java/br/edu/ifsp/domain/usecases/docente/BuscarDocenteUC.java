package br.edu.ifsp.domain.usecases.docente;

import br.edu.ifsp.domain.entities.Acolhimento;
import br.edu.ifsp.domain.entities.Docente;

import java.util.List;
import java.util.Optional;

public class BuscarDocenteUC {
    private DocenteDAO docenteDAO;

    public BuscarDocenteUC(DocenteDAO docenteDAO) {
        this.docenteDAO = docenteDAO;
    }

    public Optional<Docente> findOne(Integer prontuario){
        if (prontuario == null)
            throw new IllegalArgumentException("Prontuario can not be null.");
        return docenteDAO.findOne(prontuario);
    }

    public List<Docente> findAll(){
        return docenteDAO.findAll();
    }

    public Docente checkLogin(String email, Integer prontuario){
        if (prontuario == null || email == null)
            throw new IllegalArgumentException("Prontuario or email can not be null.");

        return docenteDAO.checkLogin(email, prontuario);
    }
}
