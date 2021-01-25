package br.edu.ifsp.domain.usecases.usuarioLinhaAcao;

import br.edu.ifsp.domain.entities.*;
import br.edu.ifsp.domain.usecases.acolhimento.BuscarAcolhimentoUC;
import br.edu.ifsp.domain.usecases.linhaAcao.BuscarAcaoUC;
import br.edu.ifsp.domain.usecases.usuario.BuscarUsuarioUC;
import br.edu.ifsp.domain.usecases.utils.EntityNotFoundException;

public class CadastrarUsuarioLinhaAcaoUC {
    private UsuarioLinhaAcaoDAO usuarioLinhaAcaoDAO;
    private BuscarAcaoUC buscarAcaoUC;
    private BuscarUsuarioUC buscarUsuarioUC;
    private BuscarAcolhimentoUC buscarAcolhimentoUC;

    public CadastrarUsuarioLinhaAcaoUC(UsuarioLinhaAcaoDAO usuarioLinhaAcaoDAO, BuscarAcaoUC buscarAcaoUC,
                                       BuscarUsuarioUC buscarUsuarioUC, BuscarAcolhimentoUC buscarAcolhimentoUC) {
        this.usuarioLinhaAcaoDAO = usuarioLinhaAcaoDAO;
        this.buscarAcaoUC = buscarAcaoUC;
        this.buscarUsuarioUC = buscarUsuarioUC;
        this.buscarAcolhimentoUC = buscarAcolhimentoUC;
    }

    public Integer cadastraUsuarioLinhaAcao(UsuarioLinhaAcao usuarioLinhaAcao){

        return usuarioLinhaAcaoDAO.create(usuarioLinhaAcao);
    }
}
