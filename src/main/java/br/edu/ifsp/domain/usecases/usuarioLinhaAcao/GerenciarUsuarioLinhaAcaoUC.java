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

    public boolean gerenciaUsuarioLinhaAcao(Integer usuarioLinhaAcaoId, Status status){
        if(usuarioLinhaAcaoId == null)
            throw new IllegalArgumentException("Id do usuario linha de acao nao pode ser nulo");

        UsuarioLinhaAcao usuarioLinhaAcao = buscarUsuarioLinhaAcaoUC.findOne(usuarioLinhaAcaoId).
                orElseThrow(() -> new EntityNotFoundException("Usuario linha de acao nao existe"));

        usuarioLinhaAcao.setStatus(status);

        return usuarioLinhaAcaoDAO.update(usuarioLinhaAcao);
    }
}
