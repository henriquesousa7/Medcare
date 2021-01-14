package br.edu.ifsp.domain.usecases.acolhimento;

import br.edu.ifsp.domain.entities.Acolhimento;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class AcolhimentoInputValidator extends Validator<Acolhimento> {
    @Override
    public Notification validate(Acolhimento acolhimento) {
        Notification notification = new Notification();

        if(acolhimento == null){
            notification.addError("Acolhimento is null");
            return notification;
        }

        if(nullOrEmpty(acolhimento.getCpf())){
            notification.addError("CPF is null or empty");
        }
        if(nullOrEmpty(acolhimento.getEmail())){
            notification.addError("Email is null or empty");
        }
        if(nullOrEmpty(acolhimento.getNome())){
            notification.addError("Nome is null or empty");
        }
        if(nullOrEmpty(acolhimento.getProntuario())){
            notification.addError("Prontuario is null or empty");
        }
        if(nullOrEmpty(acolhimento.getTelefone())){
            notification.addError("Telefone is null or empty");
        }
        return notification;
    }
}
