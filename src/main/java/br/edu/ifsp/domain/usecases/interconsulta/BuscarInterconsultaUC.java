package br.edu.ifsp.domain.usecases.interconsulta;

import br.edu.ifsp.domain.entities.Docente;
import br.edu.ifsp.domain.entities.InterConsulta;
import br.edu.ifsp.domain.usecases.docente.DocenteDAO;

import java.util.List;
import java.util.Optional;

public class BuscarInterconsultaUC {
    private InterConsultaDAO interConsultaDAO;

    public BuscarInterconsultaUC(InterConsultaDAO interConsultaDAO) {
        this.interConsultaDAO = interConsultaDAO;
    }

    public Optional<InterConsulta> findOne(Integer id){
        if (id == null)
            throw new IllegalArgumentException("Id can not be null.");
        return interConsultaDAO.findOne(id);
    }

    public List<InterConsulta> findAll(){
        return interConsultaDAO.findAll();
    }

    public List<InterConsulta> findByDocente(Integer prontuario) {
        if (prontuario == null)
            throw new IllegalArgumentException("Prontuario can not be null.");

        return interConsultaDAO.findByDocente(prontuario);
    }
}
