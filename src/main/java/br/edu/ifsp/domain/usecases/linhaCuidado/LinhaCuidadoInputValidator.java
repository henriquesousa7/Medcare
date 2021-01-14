package br.edu.ifsp.domain.usecases.linhaCuidado;

import br.edu.ifsp.domain.entities.LinhaCuidado;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class LinhaCuidadoInputValidator extends Validator<LinhaCuidado> {
    @Override
    public Notification validate(LinhaCuidado linhaCuidado) {
        Notification notification = new Notification();

        if(linhaCuidado == null) {
            notification.addError("Linha cuidado is null");
            return notification;
        }

        if(nullOrEmpty(linhaCuidado.getNome()))
            notification.addError("Nome is null or empty");

        if(nullOrEmpty(linhaCuidado.getDescricao()))
            notification.addError("Descricao is null or empty");

        return notification;
    }
}
