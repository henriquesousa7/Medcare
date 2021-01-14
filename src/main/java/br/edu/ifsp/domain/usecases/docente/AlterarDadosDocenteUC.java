package br.edu.ifsp.domain.usecases.docente;

import br.edu.ifsp.domain.entities.Docente;
import br.edu.ifsp.domain.usecases.utils.EntityNotFoundException;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class AlterarDadosDocenteUC {
    private DocenteDAO docenteDAO;

    public AlterarDadosDocenteUC(DocenteDAO docenteDAO) {
        this.docenteDAO = docenteDAO;
    }

    public boolean alterarDocente(Docente docente){
        Validator<Docente> validator = new DocenteInputValidator();
        Notification notification = validator.validate(docente);

        if(notification.hasErros())
            throw new IllegalArgumentException(notification.errorMessage());

        Integer prontuario = docente.getProntuario();

        if(docenteDAO.findOne(prontuario).isEmpty())
            throw new EntityNotFoundException("Docente nao existe");

        return docenteDAO.update(docente);
    }
}
