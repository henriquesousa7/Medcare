package br.edu.ifsp.domain.usecases.interconsulta;

import br.edu.ifsp.domain.entities.InterConsulta;
import br.edu.ifsp.domain.entities.Status;

import java.util.Optional;

public class GerenciarInterConsultaUC {
    private InterConsultaDAO interConsultaDAO;

    public GerenciarInterConsultaUC(InterConsultaDAO interConsultaDAO) {
        this.interConsultaDAO = interConsultaDAO;
    }

    public boolean gerenciaInterConsulta(Integer idInterConsulta, Status status) {
        if(idInterConsulta == null || status == null)
            throw new IllegalArgumentException("Id da InterConsulta nao pode ser nulo");

        Optional<InterConsulta> interConsultaOP = interConsultaDAO.findOne(idInterConsulta);

        InterConsulta interConsulta = interConsultaOP.get();
        interConsulta.setStatus(status);

        return interConsultaDAO.update(interConsulta);
    }
}
