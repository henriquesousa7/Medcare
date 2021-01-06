package br.edu.ifsp.domain.usecases.atendimento;

import br.edu.ifsp.domain.entities.Atendimento;
import br.edu.ifsp.domain.entities.Discente;
import br.edu.ifsp.domain.entities.Status;
import br.edu.ifsp.domain.entities.UsuarioLinhaAcao;
import br.edu.ifsp.domain.usecases.discente.BuscarDiscenteUC;
import br.edu.ifsp.domain.usecases.usuarioLinhaAcao.BuscarUsuarioLinhaAcaoUC;
import br.edu.ifsp.domain.usecases.utils.EntityNotFoundException;

import java.time.LocalDateTime;

public class AgendarAtendimentoUC {
    private AtendimentoDAO atendimentoDAO;
    private BuscarUsuarioLinhaAcaoUC buscarUsuarioLinhaAcaoUC;
    private BuscarDiscenteUC buscarDiscenteUC;

    public AgendarAtendimentoUC(AtendimentoDAO atendimentoDAO, BuscarUsuarioLinhaAcaoUC buscarUsuarioLinhaAcaoUC, BuscarDiscenteUC buscarDiscenteUC) {
        this.atendimentoDAO = atendimentoDAO;
        this.buscarUsuarioLinhaAcaoUC = buscarUsuarioLinhaAcaoUC;
        this.buscarDiscenteUC = buscarDiscenteUC;
    }

    public Integer agendaAtendimento(Atendimento atendimento, Integer idUsuarioLinhaAcao, Integer prontuarioDiscente){
        if(idUsuarioLinhaAcao == null || prontuarioDiscente == null)
            throw new IllegalArgumentException("Valores para atendimento nao podem ser nulos");

        UsuarioLinhaAcao usuarioLinhaAcao = buscarUsuarioLinhaAcaoUC.findOne(idUsuarioLinhaAcao).
                orElseThrow(() -> new EntityNotFoundException("Usuario linha de acao nao existe"));

        Discente discente = buscarDiscenteUC.findOne(prontuarioDiscente).
                orElseThrow(() -> new EntityNotFoundException("Discente nao existe"));

        atendimento.setUsuarioLinhaAcao(usuarioLinhaAcao);
        atendimento.setStatus(Status.AGUARDANDO);
        atendimento.setDiscenteResponsavel(discente);

        return atendimentoDAO.create(atendimento);

    }
}
