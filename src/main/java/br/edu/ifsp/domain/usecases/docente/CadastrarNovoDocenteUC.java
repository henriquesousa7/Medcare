package br.edu.ifsp.domain.usecases.docente;

import br.edu.ifsp.domain.entities.Docente;
import br.edu.ifsp.domain.usecases.utils.EntityAlreadyExistsException;

public class CadastrarNovoDocenteUC {
    private DocenteDAO docenteDAO;

    public CadastrarNovoDocenteUC(DocenteDAO docenteDAO) {
        this.docenteDAO = docenteDAO;
    }

    public Integer salvarDocente(Docente docente){
        Integer prontuarioDoc = docente.getProntuario();

        if(docenteDAO.findOne(prontuarioDoc).isPresent())
            throw new EntityAlreadyExistsException("Docente ja existe");

        return docenteDAO.create(docente);
    }
}
