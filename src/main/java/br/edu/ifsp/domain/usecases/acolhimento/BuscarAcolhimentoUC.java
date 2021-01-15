package br.edu.ifsp.domain.usecases.acolhimento;

import br.edu.ifsp.domain.entities.Acolhimento;
import br.edu.ifsp.domain.entities.Docente;

import java.util.List;
import java.util.Optional;

public class BuscarAcolhimentoUC {
    private AcolhimentoDAO acolhimentoDAO;

    public BuscarAcolhimentoUC(AcolhimentoDAO acolhimentoDAO) {
        this.acolhimentoDAO = acolhimentoDAO;
    }

    public Optional<Acolhimento> findOne(Integer prontuario){
        if (prontuario == null)
            throw new IllegalArgumentException("Prontuario can not be null.");
        return acolhimentoDAO.findOne(prontuario);
    }

    public List<Acolhimento> findAll(){
        return acolhimentoDAO.findAll();
    }
}
