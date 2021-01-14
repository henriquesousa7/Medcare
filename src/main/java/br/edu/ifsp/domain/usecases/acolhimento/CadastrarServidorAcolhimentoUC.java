package br.edu.ifsp.domain.usecases.acolhimento;

import br.edu.ifsp.domain.entities.Acolhimento;
import br.edu.ifsp.domain.usecases.utils.EntityAlreadyExistsException;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class CadastrarServidorAcolhimentoUC {

    private AcolhimentoDAO acolhimentoDAO;

    public CadastrarServidorAcolhimentoUC(AcolhimentoDAO acolhimentoDAO) {
        this.acolhimentoDAO = acolhimentoDAO;
    }

    public String cadastraServidor(Acolhimento acolhimento){
        Validator<Acolhimento> validator = new AcolhimentoInputValidator();
        Notification notification = validator.validate(acolhimento);

        if(notification.hasErros())
            throw new IllegalArgumentException(notification.errorMessage());

        String prontuario = acolhimento.getProntuario();

        if (acolhimentoDAO.findOne(prontuario).isPresent())
            throw new EntityAlreadyExistsException("Esse acolhimento ja existe");

        return acolhimentoDAO.create(acolhimento);
    }
}
