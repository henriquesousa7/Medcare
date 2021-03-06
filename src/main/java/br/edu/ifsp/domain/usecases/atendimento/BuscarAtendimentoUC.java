package br.edu.ifsp.domain.usecases.atendimento;

import br.edu.ifsp.domain.entities.Atendimento;

import java.util.List;
import java.util.Optional;

public class BuscarAtendimentoUC {
    private AtendimentoDAO atendimentoDAO;

    public BuscarAtendimentoUC(AtendimentoDAO atendimentoDAO) {
        this.atendimentoDAO = atendimentoDAO;
    }

    public Optional<Atendimento> findOne(Integer id){
        if (id == null)
            throw new IllegalArgumentException("Id can not be null.");
        return atendimentoDAO.findOne(id);
    }

    public List<Atendimento> findAll(){
        return atendimentoDAO.findAll();
    }
}
