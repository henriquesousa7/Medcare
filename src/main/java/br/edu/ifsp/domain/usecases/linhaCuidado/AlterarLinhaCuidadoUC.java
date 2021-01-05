package br.edu.ifsp.domain.usecases.linhaCuidado;

import br.edu.ifsp.domain.entities.LinhaCuidado;
import br.edu.ifsp.domain.usecases.utils.EntityNotFoundException;

public class AlterarLinhaCuidadoUC {
    private LinhaCuidadoDAO linhaCuidadoDAO;

    public AlterarLinhaCuidadoUC(LinhaCuidadoDAO linhaCuidadoDAO) {
        this.linhaCuidadoDAO = linhaCuidadoDAO;
    }

    public boolean alteraLinhaCuidado(LinhaCuidado linhaCuidado){
        Integer id = linhaCuidado.getId();

        if(linhaCuidadoDAO.findOne(id).isEmpty())
            throw new EntityNotFoundException("Linha cuidado nao existe");

        return linhaCuidadoDAO.update(linhaCuidado);
    }
}
