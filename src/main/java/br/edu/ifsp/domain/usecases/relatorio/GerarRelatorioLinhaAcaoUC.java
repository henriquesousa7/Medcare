package br.edu.ifsp.domain.usecases.relatorio;

import br.edu.ifsp.domain.entities.Atendimento;
import br.edu.ifsp.domain.entities.Acao;
import br.edu.ifsp.domain.entities.Status;
import br.edu.ifsp.domain.usecases.atendimento.AtendimentoDAO;

import java.util.ArrayList;
import java.util.List;

public class GerarRelatorioLinhaAcaoUC {
    private AtendimentoDAO atendimentoDAO;

    public GerarRelatorioLinhaAcaoUC(AtendimentoDAO atendimentoDAO) {
        this.atendimentoDAO = atendimentoDAO;
    }

    public List<Atendimento> geraRelatorioLinhaAcao(Acao acao){
        List<Atendimento> atendimentos = new ArrayList<>();

        for (Atendimento atendimento : atendimentoDAO.findAll()) {
            if(atendimento.getUsuarioLinhaAcao().getAcao().equals(acao)){
                atendimentos.add(atendimento);
            }
        }
        return atendimentos;
    }
}
