package br.edu.ifsp.domain.usecases.linhaAcao;

import br.edu.ifsp.domain.entities.LinhaAcao;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class LinhaAcaoInputValidator extends Validator<LinhaAcao> {
    @Override
    public Notification validate(LinhaAcao linhaAcao) {
        Notification notification = new Notification();

        if(linhaAcao == null){
            notification.addError("linhao acao is null");
            return notification;
        }

        if(nullOrEmpty(linhaAcao.getNome()))
            notification.addError("Nome is null or empty");

        if(nullOrEmpty(linhaAcao.getDescricao()))
            notification.addError("Descricao is null or empty");

        return notification;
    }
}
