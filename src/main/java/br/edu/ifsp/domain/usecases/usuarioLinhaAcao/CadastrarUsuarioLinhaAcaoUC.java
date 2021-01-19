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

    public Integer cadastraUsuarioLinhaAcao(Integer usuarioLinhaAcaoId, Integer acolhimentoPront, String usuarioCpf, Integer acaoID){
        if (usuarioLinhaAcaoId == null || acolhimentoPront == null || usuarioCpf == null || acaoID == null)
            throw new IllegalArgumentException("Valores para UsuarioLinhaAcao nao podem ser nulo");

        Acolhimento acolhimento = buscarAcolhimentoUC.findOne(acolhimentoPront).
                orElseThrow(() -> new EntityNotFoundException("Acolhimento nao existe"));

        Usuario usuario = buscarUsuarioUC.findOne(usuarioCpf).
                orElseThrow(() -> new EntityNotFoundException("Usuario nao existe"));

        Acao acao = buscarAcaoUC.findOne(acaoID).
                orElseThrow(() -> new EntityNotFoundException("Linha açao nao existe"));

        UsuarioLinhaAcao usuarioLinhaAcao = new UsuarioLinhaAcao(usuarioLinhaAcaoId, acao, usuario, Status.AGUARDANDO, acolhimento);

        return usuarioLinhaAcaoDAO.create(usuarioLinhaAcao);
    }
}
