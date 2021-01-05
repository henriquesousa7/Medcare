package br.edu.ifsp.domain.usecases.linhaCuidado;

import br.edu.ifsp.domain.entities.LinhaCuidado;

import java.util.List;
import java.util.Optional;

public class BuscarLinhaCuidadoUC {
    private LinhaCuidadoDAO linhaCuidadoDAO;

    public BuscarLinhaCuidadoUC(LinhaCuidadoDAO linhaCuidadoDAO) {
        this.linhaCuidadoDAO = linhaCuidadoDAO;
    }

    public Optional<LinhaCuidado> findOne(Integer id){
        if (id == null)
            throw new IllegalArgumentException("ID can not be null.");
        return linhaCuidadoDAO.findOne(id);
    }

    public List<LinhaCuidado> findAll(){
        return linhaCuidadoDAO.findAll();
    }
}
