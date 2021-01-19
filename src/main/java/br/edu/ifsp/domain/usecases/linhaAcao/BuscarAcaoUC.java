package br.edu.ifsp.domain.usecases.linhaAcao;



import br.edu.ifsp.domain.entities.Acao;

import java.util.List;
import java.util.Optional;

public class BuscarAcaoUC {
    private AcaoDAO acaoDAO;

    public BuscarAcaoUC(AcaoDAO acaoDAO) {
        this.acaoDAO = acaoDAO;
    }

    public Optional<Acao> findOne(Integer id){
        if (id == null)
            throw new IllegalArgumentException("ID can not be null.");
        return acaoDAO.findOne(id);
    }

    public List<Acao> findAll() {
        return acaoDAO.findAll();
    }
}
