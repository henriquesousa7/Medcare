package br.edu.ifsp.domain.usecases.relatorio;

import br.edu.ifsp.domain.entities.Atendimento;
import br.edu.ifsp.domain.entities.Status;
import br.edu.ifsp.domain.usecases.atendimento.AtendimentoDAO;

import java.util.ArrayList;
import java.util.List;

public class GerarRelatorioGeralUC {
    private AtendimentoDAO atendimentoDAO;

    public GerarRelatorioGeralUC(AtendimentoDAO atendimentoDAO) {
        this.atendimentoDAO = atendimentoDAO;
    }

    public List<Atendimento> geraRelatorioGeral(){
        List<Atendimento> atendimentos = new ArrayList<>();

        for (Atendimento atendimento : atendimentoDAO.findAll()) {
            if(atendimento.getStatus().equals(Status.AGUARDANDO) || atendimento.getStatus().equals(Status.FINALIZADO)){
                atendimentos.add(atendimento);
            }
        }
        return atendimentos;
    }
}
