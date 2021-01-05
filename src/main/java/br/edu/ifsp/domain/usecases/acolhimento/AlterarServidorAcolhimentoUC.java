package br.edu.ifsp.domain.usecases.acolhimento;

import br.edu.ifsp.domain.entities.Acolhimento;
import br.edu.ifsp.domain.usecases.utils.EntityNotFoundException;

public class AlterarServidorAcolhimentoUC {
    private AcolhimentoDAO acolhimentoDAO;

    public AlterarServidorAcolhimentoUC(AcolhimentoDAO acolhimentoDAO) {
        this.acolhimentoDAO = acolhimentoDAO;
    }

    public boolean alteraServidor(Acolhimento acolhimento){
        String prontuario = acolhimento.getProntuario();

        if (acolhimentoDAO.findOne(prontuario).isEmpty())
            throw new EntityNotFoundException("Servidor acolhimento nao encontrado");

        return acolhimentoDAO.update(acolhimento);
    }
}
