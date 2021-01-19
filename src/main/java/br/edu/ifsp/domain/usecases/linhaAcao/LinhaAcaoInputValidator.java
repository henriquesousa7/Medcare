package br.edu.ifsp.domain.usecases.linhaAcao;

import br.edu.ifsp.domain.entities.Acao;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class LinhaAcaoInputValidator extends Validator<Acao> {
    @Override
    public Notification validate(Acao acao) {
        Notification notification = new Notification();

        if(acao == null){
            notification.addError("Acao is null");
            return notification;
        }

        if(nullOrEmpty(acao.getNome()))
            notification.addError("Nome is null or empty");

        if(nullOrEmpty(acao.getDescricao()))
            notification.addError("Descricao is null or empty");

        return notification;
    }
}
