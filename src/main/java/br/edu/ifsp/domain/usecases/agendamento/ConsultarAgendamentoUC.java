package br.edu.ifsp.domain.usecases.agendamento;

import br.edu.ifsp.domain.entities.Atendimento;
import br.edu.ifsp.domain.entities.Discente;
import br.edu.ifsp.domain.usecases.atendimento.AtendimentoDAO;

import java.util.*;

public class ConsultarAgendamentoUC {
    private AtendimentoDAO atendimentoDAO;

    public ConsultarAgendamentoUC(AtendimentoDAO atendimentoDAO) {
        this.atendimentoDAO = atendimentoDAO;
    }

    public List<Atendimento> consultaAgendamento(Discente discente){
        List<Atendimento> atendimentos = new ArrayList<>();

        for (Atendimento atendimento : atendimentoDAO.findAll()) {
            if(atendimento.getDiscenteResponsavel().getProntuario() == discente.getProntuario()){
                atendimentos.add(atendimento);
            }
        }
        return atendimentos;
    }
}
