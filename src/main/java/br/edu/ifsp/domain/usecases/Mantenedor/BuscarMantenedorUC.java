package br.edu.ifsp.domain.usecases.Mantenedor;

import br.edu.ifsp.domain.entities.Docente;
import br.edu.ifsp.domain.entities.Mantenedor;

public class BuscarMantenedorUC {
    private MantenedorDAO mantenedorDAO;

    public BuscarMantenedorUC(MantenedorDAO mantenedorDAO) {
        this.mantenedorDAO = mantenedorDAO;
    }

    public Mantenedor checkLogin(String email, Integer prontuario){
        if (prontuario == null || email == null)
            throw new IllegalArgumentException("Prontuario or email can not be null.");

        return mantenedorDAO.checkLogin(email, prontuario);
    }
}
