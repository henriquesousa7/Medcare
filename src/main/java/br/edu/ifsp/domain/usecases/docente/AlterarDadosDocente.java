package br.edu.ifsp.domain.usecases.docente;

import br.edu.ifsp.domain.entities.Docente;
import br.edu.ifsp.domain.usecases.utils.EntityNotFoundException;

public class AlterarDadosDocente {
    private DocenteDAO docenteDAO;

    public AlterarDadosDocente(DocenteDAO docenteDAO) {
        this.docenteDAO = docenteDAO;
    }

    public boolean alterarDocente(Docente docente){
        Integer prontuario = docente.getProntuario();

        if(docenteDAO.findOne(prontuario).isEmpty())
            throw new EntityNotFoundException("Docente nao existe");

        return docenteDAO.update(docente);
    }
}
