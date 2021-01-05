package br.edu.ifsp.domain.usecases.acolhimento;

import br.edu.ifsp.domain.entities.Acolhimento;
import br.edu.ifsp.domain.usecases.utils.EntityAlreadyExistsException;

public class CadastrarServidorAcolhimentoUC {

    private AcolhimentoDAO acolhimentoDAO;

    public CadastrarServidorAcolhimentoUC(AcolhimentoDAO acolhimentoDAO) {
        this.acolhimentoDAO = acolhimentoDAO;
    }

    public String cadastraServidor(Acolhimento acolhimento){
        String prontuario = acolhimento.getProntuario();

        if (acolhimentoDAO.findOne(prontuario).isPresent())
            throw new EntityAlreadyExistsException("Esse acolhimento ja existe");

        return acolhimentoDAO.create(acolhimento);
    }
}
