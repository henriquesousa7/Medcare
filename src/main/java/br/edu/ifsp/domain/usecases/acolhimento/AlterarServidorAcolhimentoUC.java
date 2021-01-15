package br.edu.ifsp.domain.usecases.acolhimento;

import br.edu.ifsp.domain.entities.Acolhimento;
import br.edu.ifsp.domain.usecases.utils.EntityNotFoundException;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class AlterarServidorAcolhimentoUC {
    private AcolhimentoDAO acolhimentoDAO;

    public AlterarServidorAcolhimentoUC(AcolhimentoDAO acolhimentoDAO) {
        this.acolhimentoDAO = acolhimentoDAO;
    }

    public boolean alteraServidor(Acolhimento acolhimento){
        Validator<Acolhimento> validator = new AcolhimentoInputValidator();
        Notification notification = validator.validate(acolhimento);

        if(notification.hasErros())
            throw new IllegalArgumentException(notification.errorMessage());

        Integer prontuario = acolhimento.getProntuario();

        if (acolhimentoDAO.findOne(prontuario).isEmpty())
            throw new EntityNotFoundException("Servidor acolhimento nao encontrado");

        return acolhimentoDAO.update(acolhimento);
    }
}
