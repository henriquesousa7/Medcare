package br.edu.ifsp.domain.usecases.docente;

import br.edu.ifsp.domain.entities.Docente;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class DocenteInputValidator extends Validator<Docente> {
    @Override
    public Notification validate(Docente docente) {
        Notification notification = new Notification();

        if(docente == null) {
            notification.addError("Docente is null");
            return notification;
        }

        if(nullOrEmpty(docente.getEmail())){
            notification.addError("Email is null or empty");
        }
        if(nullOrEmpty(docente.getNome())){
            notification.addError("Nome is null or empty");
        }
        if(docente.getProntuario() <= 0){
            notification.addError("Prontuario cannot be <= 0");
        }
        if(nullOrEmpty(docente.getTelefone())){
            notification.addError("Telefone is null or empty");
        }
        return notification;
    }
}
