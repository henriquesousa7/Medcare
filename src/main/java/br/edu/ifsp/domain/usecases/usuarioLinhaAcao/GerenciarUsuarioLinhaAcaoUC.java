package br.edu.ifsp.domain.usecases.usuarioLinhaAcao;

import br.edu.ifsp.domain.entities.Status;
import br.edu.ifsp.domain.entities.UsuarioLinhaAcao;
import br.edu.ifsp.domain.usecases.utils.EntityNotFoundException;

public class GerenciarUsuarioLinhaAcaoUC {
    private UsuarioLinhaAcaoDAO usuarioLinhaAcaoDAO;
    private BuscarUsuarioLinhaAcaoUC buscarUsuarioLinhaAcaoUC;

    public GerenciarUsuarioLinhaAcaoUC(UsuarioLinhaAcaoDAO usuarioLinhaAcaoDAO, BuscarUsuarioLinhaAcaoUC buscarUsuarioLinhaAcaoUC) {
        this.usuarioLinhaAcaoDAO = usuarioLinhaAcaoDAO;
        this.buscarUsuarioLinhaAcaoUC = buscarUsuarioLinhaAcaoUC;
    }

    public boolean gerenciaUsuarioLinhaAcao(UsuarioLinhaAcao usuarioLinhaAcao){
        return usuarioLinhaAcaoDAO.update(usuarioLinhaAcao);
    }
}
