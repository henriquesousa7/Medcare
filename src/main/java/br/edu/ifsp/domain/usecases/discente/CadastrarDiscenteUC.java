package br.edu.ifsp.domain.usecases.discente;

import br.edu.ifsp.domain.entities.Discente;
import br.edu.ifsp.domain.entities.LinhaAcao;
import br.edu.ifsp.domain.entities.LinhaCuidado;
import br.edu.ifsp.domain.usecases.linhaAcao.BuscarLinhaAcaoUC;
import br.edu.ifsp.domain.usecases.utils.EntityNotFoundException;

public class CadastrarDiscenteUC {
    private DiscenteDAO discenteDAO;
    private BuscarLinhaAcaoUC buscarLinhaAcaoUC;

    public CadastrarDiscenteUC(DiscenteDAO discenteDAO, BuscarLinhaAcaoUC buscarLinhaAcaoUC) {
        this.discenteDAO = discenteDAO;
        this.buscarLinhaAcaoUC = buscarLinhaAcaoUC;
    }

    public Integer cadastraDiscente(Discente discente, Integer linhaAcaoID){
        if(linhaAcaoID == null)
            throw new IllegalArgumentException("Id da linha de acao nao pode ser nulo");

        LinhaAcao linhaAcao = buscarLinhaAcaoUC.findOne(linhaAcaoID).
                orElseThrow(() -> new EntityNotFoundException("Linha acao nao existe"));

        discente.setLinhaAcao(linhaAcao);

        return discenteDAO.create(discente);
    }
}
