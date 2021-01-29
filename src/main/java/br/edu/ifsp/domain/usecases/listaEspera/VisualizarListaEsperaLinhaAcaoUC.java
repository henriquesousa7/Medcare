package br.edu.ifsp.domain.usecases.listaEspera;

import br.edu.ifsp.domain.entities.*;
import br.edu.ifsp.domain.usecases.atendimento.AtendimentoDAO;
import br.edu.ifsp.domain.usecases.discente.DiscenteInputValidator;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

import java.util.ArrayList;
import java.util.List;

public class VisualizarListaEsperaLinhaAcaoUC {
    private AtendimentoDAO atendimentoDAO;

    public VisualizarListaEsperaLinhaAcaoUC(AtendimentoDAO atendimentoDAO) {
        this.atendimentoDAO = atendimentoDAO;
    }

    public List<Atendimento> geraListaEspera(Acao acao, Discente discente){
        List<Atendimento> atendimentos = new ArrayList<>();

        Validator<Discente> validator = new DiscenteInputValidator();
        Notification notification = validator.validate(discente);

        if(notification.hasErros())
            throw new IllegalArgumentException(notification.errorMessage());

        for (Atendimento atendimento : atendimentoDAO.findByDiscente(discente.getProntuario())) {
            if (atendimento.getUsuarioLinhaAcao().getAcao().getId().equals(acao.getId())) {
                if (atendimento.getStatus().equals(Status.AGUARDANDO))
                    atendimentos.add(atendimento);
            }
        }
        return atendimentos;
    }
}
