package br.edu.ifsp.domain.usecases.atendimento;

import br.edu.ifsp.domain.entities.Atendimento;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class AtendimentoInputValidator extends Validator<Atendimento> {
    @Override
    public Notification validate(Atendimento atendimento) {
        Notification notification = new Notification();

        if (atendimento == null) {
            notification.addError("Book is null");
            return notification;
        }

        if(atendimento.getData() == null)
            notification.addError("Data is null");

        return notification;
    }
}
