package br.edu.ifsp.domain.usecases.docente;

import br.edu.ifsp.domain.entities.Docente;
import br.edu.ifsp.domain.usecases.utils.EntityAlreadyExistsException;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class CadastrarNovoDocenteUC {
    private DocenteDAO docenteDAO;

    public CadastrarNovoDocenteUC(DocenteDAO docenteDAO) {
        this.docenteDAO = docenteDAO;
    }

    public Integer salvarDocente(Docente docente){
        Validator<Docente> validator = new DocenteInputValidator();
        Notification notification = validator.validate(docente);

        if(notification.hasErros())
            throw new IllegalArgumentException(notification.errorMessage());

        Integer prontuarioDoc = docente.getProntuario();

        if(docenteDAO.findOne(prontuarioDoc).isPresent())
            throw new EntityAlreadyExistsException("Docente ja existe");

        return docenteDAO.create(docente);
    }
}
