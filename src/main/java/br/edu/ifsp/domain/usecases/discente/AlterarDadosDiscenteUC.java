package br.edu.ifsp.domain.usecases.discente;

import br.edu.ifsp.domain.entities.Discente;
import br.edu.ifsp.domain.entities.LinhaAcao;
import br.edu.ifsp.domain.usecases.linhaAcao.BuscarLinhaAcaoUC;
import br.edu.ifsp.domain.usecases.utils.EntityNotFoundException;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class AlterarDadosDiscenteUC {
    private DiscenteDAO discenteDAO;
    private BuscarLinhaAcaoUC buscarLinhaAcaoUC;

    public AlterarDadosDiscenteUC(DiscenteDAO discenteDAO, BuscarLinhaAcaoUC buscarLinhaAcaoUC) {
        this.discenteDAO = discenteDAO;
        this.buscarLinhaAcaoUC = buscarLinhaAcaoUC;
    }

    public boolean alterarDiscente(Discente discente, Integer linhaAcaoID) {
        Validator<Discente> validator = new DiscenteInputValidator();
        Notification notification = validator.validate(discente);

        if(notification.hasErros())
            throw new IllegalArgumentException(notification.errorMessage());

        if(linhaAcaoID == null)
            throw new IllegalArgumentException("Id da linha de acao nao pode ser nulo");

        LinhaAcao linhaAcao = buscarLinhaAcaoUC.findOne(linhaAcaoID).
                orElseThrow(() -> new EntityNotFoundException("Linha acao nao existe"));

        discente.setLinhaAcao(linhaAcao);

        return discenteDAO.update(discente);
    }
}
