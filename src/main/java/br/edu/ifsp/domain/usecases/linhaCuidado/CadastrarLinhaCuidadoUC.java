package br.edu.ifsp.domain.usecases.linhaCuidado;

import br.edu.ifsp.domain.entities.LinhaCuidado;
import br.edu.ifsp.domain.usecases.utils.EntityAlreadyExistsException;

public class CadastrarLinhaCuidadoUC {
    private LinhaCuidadoDAO linhaCuidadoDAO;

    public CadastrarLinhaCuidadoUC(LinhaCuidadoDAO linhaCuidadoDAO) {
        this.linhaCuidadoDAO = linhaCuidadoDAO;
    }

    public Integer cadastraLinhaCuidado(LinhaCuidado linhaCuidado){
        int id = linhaCuidado.getId();

        if(linhaCuidadoDAO.findOne(id).isPresent())
            throw new EntityAlreadyExistsException("Linha de cuidado ja existe");

        return linhaCuidadoDAO.create(linhaCuidado);
    }
}
