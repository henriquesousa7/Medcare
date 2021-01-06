package br.edu.ifsp.domain.usecases.interconsulta;

import br.edu.ifsp.domain.entities.*;
import br.edu.ifsp.domain.usecases.docente.BuscarDocenteUC;
import br.edu.ifsp.domain.usecases.docente.DocenteDAO;
import br.edu.ifsp.domain.usecases.linhaAcao.BuscarLinhaAcaoUC;
import br.edu.ifsp.domain.usecases.linhaAcao.LinhaAcaoDAO;
import br.edu.ifsp.domain.usecases.usuario.BuscarUsuarioUC;
import br.edu.ifsp.domain.usecases.usuario.UsuarioDAO;
import br.edu.ifsp.domain.usecases.utils.EntityNotFoundException;

public class SolicitarInterConsultaUC {
    private InterConsultaDAO interConsultaDAO;
    private BuscarLinhaAcaoUC buscarLinhaAcaoUC;
    private BuscarUsuarioUC buscarUsuarioUC;
    private BuscarDocenteUC buscarDocenteUC;

    public SolicitarInterConsultaUC(InterConsultaDAO interConsultaDAO, BuscarLinhaAcaoUC buscarLinhaAcaoUC, BuscarUsuarioUC buscarUsuarioUC, BuscarDocenteUC buscarDocenteUC) {
        this.interConsultaDAO = interConsultaDAO;
        this.buscarLinhaAcaoUC = buscarLinhaAcaoUC;
        this.buscarUsuarioUC = buscarUsuarioUC;
        this.buscarDocenteUC = buscarDocenteUC;
    }

    public Integer solicitaInterConsulta(Integer idInterConsulta, Integer idLinhaAcao, String cpfUsuario, Integer prontDocente){
        if(idInterConsulta == null || idLinhaAcao == null || cpfUsuario == null || prontDocente == null)
            throw new IllegalArgumentException("Valores para interconsulta nao podem ser nulos");

        LinhaAcao linhaAcao = buscarLinhaAcaoUC.findOne(idLinhaAcao).
                orElseThrow(() -> new EntityNotFoundException("Linha de acao nao existe"));

        Usuario usuario = buscarUsuarioUC.findOne(cpfUsuario).
                orElseThrow(() -> new EntityNotFoundException("Usuario nao existe"));

        Docente docente = buscarDocenteUC.findOne(prontDocente).
                orElseThrow(() -> new EntityNotFoundException("Docente nao existe"));

        InterConsulta interConsulta = new InterConsulta(idInterConsulta, linhaAcao, usuario, docente, Status.AGUARDANDO);

        return interConsultaDAO.create(interConsulta);
    }
}
