package br.edu.ifsp.domain.usecases.relatorio;

import br.edu.ifsp.domain.entities.Atendimento;
import br.edu.ifsp.domain.entities.Discente;
import br.edu.ifsp.domain.entities.Status;
import br.edu.ifsp.domain.usecases.atendimento.AtendimentoDAO;
import br.edu.ifsp.domain.usecases.discente.DiscenteInputValidator;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

import java.util.ArrayList;
import java.util.List;

public class GerarRelatorioGeralUC {
    private AtendimentoDAO atendimentoDAO;

    public GerarRelatorioGeralUC(AtendimentoDAO atendimentoDAO) {
        this.atendimentoDAO = atendimentoDAO;
    }

    public List<Atendimento> geraRelatorioGeral(Discente discente){
        List<Atendimento> atendimentos = new ArrayList<>();

        Validator<Discente> validator = new DiscenteInputValidator();
        Notification notification = validator.validate(discente);

        if(notification.hasErros())
            throw new IllegalArgumentException(notification.errorMessage());

        for (Atendimento atendimento : atendimentoDAO.findByDiscente(discente.getProntuario())) {
            if (atendimento.getStatus().equals(Status.FINALIZADO)) {
                atendimentos.add(atendimento);
            }
        }
        return atendimentos;
    }
}
