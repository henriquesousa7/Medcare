package br.edu.ifsp.domain.usecases.atendimento;

import br.edu.ifsp.domain.entities.Atendimento;
import br.edu.ifsp.domain.entities.Discente;
import br.edu.ifsp.domain.entities.Status;
import br.edu.ifsp.domain.entities.UsuarioLinhaAcao;
import br.edu.ifsp.domain.usecases.discente.BuscarDiscenteUC;
import br.edu.ifsp.domain.usecases.usuarioLinhaAcao.BuscarUsuarioLinhaAcaoUC;
import br.edu.ifsp.domain.usecases.utils.EntityNotFoundException;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

import java.time.LocalDateTime;

public class AgendarAtendimentoUC {
    private AtendimentoDAO atendimentoDAO;

    public AgendarAtendimentoUC(AtendimentoDAO atendimentoDAO) {
        this.atendimentoDAO = atendimentoDAO;
    }

    public Integer agendaAtendimento(Atendimento atendimento){
        Validator<Atendimento> validator = new AtendimentoInputValidator();
        Notification notification = validator.validate(atendimento);

        if(notification.hasErros())
            throw new IllegalArgumentException(notification.errorMessage());

        return atendimentoDAO.create(atendimento);
    }
}
