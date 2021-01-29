package br.edu.ifsp.domain.usecases.interconsulta;

import br.edu.ifsp.domain.entities.InterConsulta;
import br.edu.ifsp.domain.entities.Status;
import br.edu.ifsp.domain.usecases.usuarioLinhaAcao.UsuarioLinhaAcaoDAO;

import java.util.Optional;

public class GerenciarInterConsultaUC {
    private InterConsultaDAO interConsultaDAO;
    private UsuarioLinhaAcaoDAO usuarioLinhaAcaoDAO;

    public GerenciarInterConsultaUC(InterConsultaDAO interConsultaDAO, UsuarioLinhaAcaoDAO usuarioLinhaAcaoDAO) {
        this.interConsultaDAO = interConsultaDAO;
        this.usuarioLinhaAcaoDAO = usuarioLinhaAcaoDAO;
    }

    public boolean gerenciaInterConsulta(InterConsulta interConsulta, String response) {
        if(interConsulta == null || response == null)
            throw new IllegalArgumentException("InterConsulta nao pode ser nulo");

        if(response.equalsIgnoreCase("aceito")) {
            usuarioLinhaAcaoDAO.updateByUsuario(interConsulta.getAcao().getId(), interConsulta.getUsuario().getCpf());
            interConsultaDAO.delete(interConsulta);
        } else {
            interConsultaDAO.delete(interConsulta);
        }
        return true;
    }
}
