package br.edu.ifsp.domain.usecases.discente;

import br.edu.ifsp.domain.entities.Discente;
import br.edu.ifsp.domain.entities.Acao;
import br.edu.ifsp.domain.usecases.linhaAcao.BuscarAcaoUC;
import br.edu.ifsp.domain.usecases.utils.EntityNotFoundException;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class CadastrarDiscenteUC {
    private DiscenteDAO discenteDAO;
    private BuscarAcaoUC buscarAcaoUC;

    public CadastrarDiscenteUC(DiscenteDAO discenteDAO, BuscarAcaoUC buscarAcaoUC) {
        this.discenteDAO = discenteDAO;
        this.buscarAcaoUC = buscarAcaoUC;
    }

    public Integer cadastraDiscente(Discente discente, Integer acaoID){
        Validator<Discente> validator = new DiscenteInputValidator();
        Notification notification = validator.validate(discente);

        if(notification.hasErros())
            throw new IllegalArgumentException(notification.errorMessage());

        if(acaoID == null)
            throw new IllegalArgumentException("Id da acao nao pode ser nulo");

        Acao acao = buscarAcaoUC.findOne(acaoID).
                orElseThrow(() -> new EntityNotFoundException("acao nao existe"));

        discente.setAcao(acao);

        return discenteDAO.create(discente);
    }
}
