package br.edu.ifsp.domain.usecases.interconsulta;

import br.edu.ifsp.domain.entities.*;
import br.edu.ifsp.domain.usecases.atendimento.BuscarAtendimentoUC;
import br.edu.ifsp.domain.usecases.docente.BuscarDocenteUC;
import br.edu.ifsp.domain.usecases.docente.DocenteDAO;
import br.edu.ifsp.domain.usecases.linhaAcao.BuscarLinhaAcaoUC;
import br.edu.ifsp.domain.usecases.linhaAcao.LinhaAcaoDAO;
import br.edu.ifsp.domain.usecases.usuario.BuscarUsuarioUC;
import br.edu.ifsp.domain.usecases.usuario.UsuarioDAO;
import br.edu.ifsp.domain.usecases.utils.EntityNotFoundException;

public class SolicitarInterConsultaUC {
    private InterConsultaDAO interConsultaDAO;
    private BuscarAtendimentoUC buscarAtendimentoUC;
    private BuscarUsuarioUC buscarUsuarioUC;
    private BuscarDocenteUC buscarDocenteUC;

    public SolicitarInterConsultaUC(InterConsultaDAO interConsultaDAO, BuscarAtendimentoUC buscarAtendimentoUC, BuscarUsuarioUC buscarUsuarioUC, BuscarDocenteUC buscarDocenteUC) {
        this.interConsultaDAO = interConsultaDAO;
        this.buscarAtendimentoUC = buscarAtendimentoUC;
        this.buscarUsuarioUC = buscarUsuarioUC;
        this.buscarDocenteUC = buscarDocenteUC;
    }

    public Integer solicitaInterConsulta(Integer idInterConsulta, Integer idAtendimento, String cpfUsuario, Integer prontDocente){
        if(idInterConsulta == null || idAtendimento == null || cpfUsuario == null || prontDocente == null)
            throw new IllegalArgumentException("Valores para interconsulta nao podem ser nulos");

        Atendimento atendimento = buscarAtendimentoUC.findOne(idAtendimento).
                orElseThrow(() -> new EntityNotFoundException("Atendimento nao existe"));

        Usuario usuario = buscarUsuarioUC.findOne(cpfUsuario).
                orElseThrow(() -> new EntityNotFoundException("Usuario nao existe"));

        Docente docente = buscarDocenteUC.findOne(prontDocente).
                orElseThrow(() -> new EntityNotFoundException("Docente nao existe"));

        InterConsulta interConsulta = new InterConsulta(idInterConsulta, atendimento, usuario, docente, Status.AGUARDANDO);

        return interConsultaDAO.create(interConsulta);
    }
}
