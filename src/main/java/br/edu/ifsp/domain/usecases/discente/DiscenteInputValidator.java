package br.edu.ifsp.domain.usecases.discente;

import br.edu.ifsp.domain.entities.Discente;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class DiscenteInputValidator extends Validator<Discente> {
    @Override
    public Notification validate(Discente discente) {
        Notification notification = new Notification();

        if(discente == null) {
            notification.addError("Discente is null");
            return notification;
        }

        if(nullOrEmpty(discente.getEmail())){
            notification.addError("Email is null or empty");
        }
        if(nullOrEmpty(discente.getNome())){
            notification.addError("Nome is null or empty");
        }
        if(discente.getProntuario() <= 0){
            notification.addError("Prontuario cannot be <= 0");
        }
        if(nullOrEmpty(discente.getTelefone())){
            notification.addError("Telefone is null or empty");
        }
        return notification;
    }
}
