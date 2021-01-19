package br.edu.ifsp.domain.usecases.linhaAcao;

import br.edu.ifsp.domain.entities.Acao;
import br.edu.ifsp.domain.usecases.utils.EntityNotFoundException;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class AlterarAcaoUC {
    private AcaoDAO acaoDAO;

    public AlterarAcaoUC(AcaoDAO acaoDAO) {
        this.acaoDAO = acaoDAO;
    }

    public boolean alteraLinhaAcao(Acao acao){
        Validator<Acao> validator = new LinhaAcaoInputValidator();
        Notification notification = validator.validate(acao);

        if(notification.hasErros())
            throw new IllegalArgumentException(notification.errorMessage());

        Integer id = acao.getId();

        if (acaoDAO.findOne(id).isEmpty())
            throw new EntityNotFoundException("Linha acao nao encontrado");

        return acaoDAO.update(acao);
    }
}
