package br.edu.ifsp.domain.usecases.atendimento;

import br.edu.ifsp.domain.entities.Atendimento;
import br.edu.ifsp.domain.usecases.discente.BuscarDiscenteUC;
import br.edu.ifsp.domain.usecases.usuarioLinhaAcao.BuscarUsuarioLinhaAcaoUC;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class AlterarAtendimentoUC {
    private AtendimentoDAO atendimentoDAO;

    public AlterarAtendimentoUC(AtendimentoDAO atendimentoDAO) {
        this.atendimentoDAO = atendimentoDAO;
    }

    public boolean alterarAtendimento(Atendimento atendimento){
        Validator<Atendimento> validator = new AtendimentoInputValidator();
        Notification notification = validator.validate(atendimento);

        if(notification.hasErros())
            throw new IllegalArgumentException(notification.errorMessage());

        return atendimentoDAO.update(atendimento);
    }
}
