package br.edu.ifsp.domain.usecases.discente;

import br.edu.ifsp.domain.entities.Discente;
import br.edu.ifsp.domain.entities.Acao;
import br.edu.ifsp.domain.usecases.linhaAcao.BuscarAcaoUC;
import br.edu.ifsp.domain.usecases.utils.EntityNotFoundException;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class AlterarDadosDiscenteUC {
    private DiscenteDAO discenteDAO;
    private BuscarAcaoUC buscarAcaoUC;

    public AlterarDadosDiscenteUC(DiscenteDAO discenteDAO, BuscarAcaoUC buscarAcaoUC) {
        this.discenteDAO = discenteDAO;
        this.buscarAcaoUC = buscarAcaoUC;
    }

    public boolean alterarDiscente(Discente discente, Integer acaoID) {
        Validator<Discente> validator = new DiscenteInputValidator();
        Notification notification = validator.validate(discente);

        if(notification.hasErros())
            throw new IllegalArgumentException(notification.errorMessage());

        if(acaoID == null)
            throw new IllegalArgumentException("Id da linha de acao nao pode ser nulo");

        Acao acao = buscarAcaoUC.findOne(acaoID).
                orElseThrow(() -> new EntityNotFoundException("Linha acao nao existe"));

        discente.setAcao(acao);

        return discenteDAO.update(discente);
    }
}
