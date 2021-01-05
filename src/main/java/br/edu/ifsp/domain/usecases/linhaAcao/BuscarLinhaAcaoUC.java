package br.edu.ifsp.domain.usecases.linhaAcao;



import br.edu.ifsp.domain.entities.LinhaAcao;

import java.util.List;
import java.util.Optional;

public class BuscarLinhaAcaoUC {
    private LinhaAcaoDAO linhaAcaoDAO;

    public BuscarLinhaAcaoUC(LinhaAcaoDAO linhaAcaoDAO) {
        this.linhaAcaoDAO = linhaAcaoDAO;
    }

    public Optional<LinhaAcao> findOne(Integer id){
        if (id == null)
            throw new IllegalArgumentException("ID can not be null.");
        return linhaAcaoDAO.findOne(id);
    }

    public List<LinhaAcao> findAll() {
        return linhaAcaoDAO.findAll();
    }
}
