package br.edu.ifsp.domain.usecases.linhaAcao;

import br.edu.ifsp.domain.entities.LinhaAcao;
import br.edu.ifsp.domain.usecases.utils.EntityNotFoundException;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class AlterarLinhaAcaoUC {
    private LinhaAcaoDAO linhaAcaoDAO;

    public AlterarLinhaAcaoUC(LinhaAcaoDAO linhaAcaoDAO) {
        this.linhaAcaoDAO = linhaAcaoDAO;
    }

    public boolean alteraLinhaAcao(LinhaAcao linhaAcao){
        Validator<LinhaAcao> validator = new LinhaAcaoInputValidator();
        Notification notification = validator.validate(linhaAcao);

        if(notification.hasErros())
            throw new IllegalArgumentException(notification.errorMessage());

        Integer id = linhaAcao.getId();

        if (linhaAcaoDAO.findOne(id).isEmpty())
            throw new EntityNotFoundException("Linha acao nao encontrado");

        return linhaAcaoDAO.update(linhaAcao);
    }
}
