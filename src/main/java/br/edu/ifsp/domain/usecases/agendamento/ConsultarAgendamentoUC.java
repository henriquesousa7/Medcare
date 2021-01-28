package br.edu.ifsp.domain.usecases.agendamento;

import br.edu.ifsp.domain.entities.Atendimento;
import br.edu.ifsp.domain.entities.Discente;
import br.edu.ifsp.domain.entities.Status;
import br.edu.ifsp.domain.usecases.atendimento.AtendimentoDAO;
import br.edu.ifsp.domain.usecases.discente.DiscenteInputValidator;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

import java.util.*;

public class ConsultarAgendamentoUC {
    private AtendimentoDAO atendimentoDAO;

    public ConsultarAgendamentoUC(AtendimentoDAO atendimentoDAO) {
        this.atendimentoDAO = atendimentoDAO;
    }

    public List<Atendimento> consultaAgendamento(Discente discente){
        Validator<Discente> validator = new DiscenteInputValidator();
        Notification notification = validator.validate(discente);

        List<Atendimento> atendimentos = new ArrayList<>();

        if(notification.hasErros())
            throw new IllegalArgumentException(notification.errorMessage());

        for (Atendimento atendimento : atendimentoDAO.findByDiscente(discente.getProntuario())) {
            if (atendimento.getStatus().equals(Status.ANDAMENTO)) {
                atendimentos.add(atendimento);
            }
        }
        return atendimentos;
    }
}
