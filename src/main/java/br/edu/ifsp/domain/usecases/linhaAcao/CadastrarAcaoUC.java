package br.edu.ifsp.domain.usecases.linhaAcao;

import br.edu.ifsp.domain.entities.Acao;
import br.edu.ifsp.domain.entities.Docente;
import br.edu.ifsp.domain.entities.LinhaCuidado;
import br.edu.ifsp.domain.usecases.docente.BuscarDocenteUC;
import br.edu.ifsp.domain.usecases.linhaCuidado.BuscarLinhaCuidadoUC;
import br.edu.ifsp.domain.usecases.utils.EntityNotFoundException;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class CadastrarAcaoUC {
    private AcaoDAO acaoDAO;
    private BuscarDocenteUC buscarDocenteUC;

    public CadastrarAcaoUC(AcaoDAO acaoDAO, BuscarDocenteUC buscarDocenteUC) {
        this.acaoDAO = acaoDAO;
        this.buscarDocenteUC = buscarDocenteUC;
    }

    public Integer cadastraLinhaAcao(Acao acao, Integer prontuarioDocente){
        Validator<Acao> validator = new LinhaAcaoInputValidator();
        Notification notification = validator.validate(acao);

        if(notification.hasErros())
            throw new IllegalArgumentException(notification.errorMessage());

        if (prontuarioDocente == null)
            throw new IllegalArgumentException("Prontuario do Docente nao pode ser nulo");


        Docente docente = buscarDocenteUC.findOne(prontuarioDocente).
                orElseThrow(() -> new EntityNotFoundException("Docente nao existe"));


        acao.setResponsavel(docente);

        return acaoDAO.create(acao);
    }
}
